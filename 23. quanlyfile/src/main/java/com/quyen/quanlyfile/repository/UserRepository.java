package com.quyen.quanlyfile.repository;

import com.quyen.quanlyfile.Util.StringUtil;
import com.quyen.quanlyfile.model.request.SearchUserRequest;
import com.quyen.quanlyfile.model.response.UserDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@AllArgsConstructor
public class UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UserDetailResponse> searchUser(SearchUserRequest request) {
        String sql = "WITH RECURSIVE data_user AS (\n" +
                "\tSELECT id, name \n" +
                "\tFROM users u\n" +
                "\t{search_condition}\n" +
                "\tLIMIT {limit_number}\n" +
                "\tOFFSET {offset_number}\n" +
                "),\n" +
                "count_user AS (\n" +
                "\tSELECT COUNT(*) total_record\n" +
                "\tFROM users u\n" +
                "\t{search_condition}\n" +
                ")\n" +
                "SELECT d.*, c.total_record totalRecord \n" +
                "FROM data_user d, count_user c";

        String searchCondition = " where 1 = 1 ";
        Map<String, Object> parameters = new HashMap<>();
        if (StringUtils.hasText(request.getName())) {
            searchCondition += "and u.name like :name \n";
            parameters.put("name", "%" + StringUtil.escapeWildCardCharacter(request.getName()) + "%");
        }

        sql = sql.replace("{search_condition}", searchCondition);
        sql = sql.replace("{limit_number}", Integer.valueOf(request.getPageSize()).toString());
        String offsetNumber = "0";
        if (request.getCurrentPage() != 0) {
            offsetNumber = Integer.valueOf(request.getCurrentPage() * request.getPageSize()).toString();
        }
        sql = sql.replace("{offset_number}", offsetNumber);

        return namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(UserDetailResponse.class));
    }


}
