package com.quyen.test.repository;

import com.quyen.test.Util.StringUtil;
import com.quyen.test.model.request.SearchAppointmentRequest;
import com.quyen.test.model.request.SearchProductRequest;
import com.quyen.test.model.response.AppointmentDetailResponse;
import com.quyen.test.model.response.ProductDetailResponse;
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
public class ProductRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ProductDetailResponse> searchProduct(SearchProductRequest request) {
        String sql = "WITH RECURSIVE data_product AS (\n" +
                "\tSELECT id, name, price, description, image \n" +
                "\tFROM products p\n" +
                "\t{search_condition}\n" +
                "\tLIMIT {limit_number}\n" +
                "\tOFFSET {offset_number}\n" +
                "),\n" +
                "count_product AS (\n" +
                "\tSELECT COUNT(*) total_record\n" +
                "\tFROM products p\n" +
                "\t{search_condition}\n" +
                ")\n" +
                "SELECT d.*, c.total_record totalRecord \n" +
                "FROM data_product d, count_product c";

        String searchCondition = " where 1 = 1 ";
        Map<String, Object> parameters = new HashMap<>();
        if (StringUtils.hasText(request.getName())) {
            searchCondition += "and p.name like :name \n";
            parameters.put("name", "%" + StringUtil.escapeWildCardCharacter(request.getName()) + "%");
        }

        sql = sql.replace("{search_condition}", searchCondition);
        sql = sql.replace("{limit_number}", Integer.valueOf(request.getPageSize()).toString());
        String offsetNumber = "0";
        if (request.getCurrentPage() != 0) {
            offsetNumber = Integer.valueOf(request.getCurrentPage() * request.getPageSize()).toString();
        }
        sql = sql.replace("{offset_number}", offsetNumber);

        return namedParameterJdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper<>(ProductDetailResponse.class));
    }


}
