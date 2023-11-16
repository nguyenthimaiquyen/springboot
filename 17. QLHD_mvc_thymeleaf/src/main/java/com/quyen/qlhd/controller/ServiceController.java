package com.quyen.qlhd.controller;

import com.quyen.qlhd.entity.Service;
import com.quyen.qlhd.exception.ServiceNotFoundException;
import com.quyen.qlhd.model.request.ServiceCreationRequest;
import com.quyen.qlhd.model.response.ServiceDetailResponse;
import com.quyen.qlhd.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping("/service")
    public String service(Model model) {
        List<Service> services = serviceService.getAll();
        model.addAttribute("services", services);
        return "service/services";
    }

    @GetMapping("/delete-service/{id}")
    public String deleteService(@PathVariable("id") int id, Model model) throws ServiceNotFoundException {
        List<Service> services = serviceService.deleteService(id);
        model.addAttribute("services", services);
        return "service/services";
    }

    @GetMapping("/create-service")
    public String forwardToServiceCreation(Model model) {
        model.addAttribute("ServiceCreationRequest", new ServiceCreationRequest());
        return "service/service-creation";
    }

    @PostMapping("/create-service")
    public String createService(@ModelAttribute("ServiceCreationRequest") @Valid ServiceCreationRequest service,
                                Model model, Errors errors) {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "service/service-creation";
        }
        List<Service> services = serviceService.createService(service);
        model.addAttribute("services", services);
        return "service/services";
    }

    @GetMapping("/update-service/{service-id}")
    public String forwardToServiceUpdate(@PathVariable("service-id") int id, Model model) throws ServiceNotFoundException {
        ServiceDetailResponse service = serviceService.findById(id);
        model.addAttribute("ServiceUpdateRequest", service);
        return "service/service-update";
    }

    @PostMapping("/update-service")
    public String updateService(@ModelAttribute("ServiceUpdateRequest") @Valid ServiceDetailResponse service,
                                Model model, Errors errors) throws ServiceNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "service/service-creation";
        }
        List<Service> services = serviceService.updateService(service);
        model.addAttribute("services", services);
        return "service/services";
    }

}
