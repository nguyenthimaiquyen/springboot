package com.quyen.test.model.response;

import com.quyen.test.statics.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDetailResponse {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String content;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime handledAt;

    private Long totalRecord;
}
