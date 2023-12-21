package com.quyen.quanlyfile.repository;

import com.quyen.quanlyfile.Util.StringUtil;
import com.quyen.quanlyfile.model.request.SearchImageRequest;
import com.quyen.quanlyfile.model.response.ImageDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@AllArgsConstructor
public class ImageRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ImageDetailResponse> searchImage(SearchImageRequest request, Integer id) {
        String sql = "WITH RECURSIVE data_image AS (\n" +
                "\tSELECT id, type, created_at createdAt \n" +
                "\tFROM images i\n" +
                "\t{search_condition}\n" +
                "\tLIMIT {limit_number}\n" +
                "\tOFFSET {offset_number}\n" +
                "),\n" +
                "count_image AS (\n" +
                "\tSELECT COUNT(*) total_record\n" +
                "\tFROM images i\n" +
                "\t{search_condition}\n" +
                ")\n" +
                "SELECT d.*, c.total_record totalRecord \n" +
                "FROM data_image d, count_image c";

        String searchCondition = " where i.user_id = {id} ";
        searchCondition = searchCondition.replace("{id}", String.valueOf(id));

        Map<String, Object> parameters = new HashMap<>();
        if (StringUtils.hasText(request.getType())) {
            searchCondition += "and i.type like :type \n";
            parameters.put("type", "%" + StringUtil.escapeWildCardCharacter(request.getType()) + "%");
        }

        sql = sql.replace("{search_condition}", searchCondition);
        sql = sql.replace("{limit_number}", Integer.valueOf(request.getPageSize()).toString());
        String offsetNumber = "0";
        if (request.getCurrentPage() != 0) {
            offsetNumber = Integer.valueOf(request.getCurrentPage() * request.getPageSize()).toString();
        }
        sql = sql.replace("{offset_number}", offsetNumber);

        return namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(ImageDetailResponse.class));
    }


}
