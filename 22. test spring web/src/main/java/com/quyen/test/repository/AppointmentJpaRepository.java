package com.quyen.test.repository;

import com.quyen.test.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {
}
