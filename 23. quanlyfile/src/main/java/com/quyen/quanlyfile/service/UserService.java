package com.quyen.quanlyfile.service;

import com.quyen.quanlyfile.model.request.SearchUserRequest;
import com.quyen.quanlyfile.model.response.UserDetailResponse;
import com.quyen.quanlyfile.model.response.UserResponse;
import com.quyen.quanlyfile.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse searchUser(SearchUserRequest request) {
        List<UserDetailResponse> data = userRepository.searchUser(request);
        Long totalElement = 0L;
        if (!CollectionUtils.isEmpty(data)) {
            totalElement = data.get(0).getTotalRecord();
        }
        double totalPageTemp = (double) totalElement / request.getPageSize();
        if (totalPageTemp > totalElement / request.getPageSize()) {
            totalPageTemp++;
        }

        return UserResponse.builder()
                .users(data)
                .totalElement(totalElement)
                .totalPage(Double.valueOf(totalPageTemp).intValue())
                .currentPage(request.getCurrentPage())
                .pageSize(request.getPageSize())
                .build();
    }


}
