package com.adri.ej31.feign;

import com.adri.ej31.profesor.infrastructure.dto.output.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "demoFeign", url = "http://localhost:8081/")
public interface IFeignServer {
    @GetMapping("profesor/{id}")
    public ProfesorOutputDTO getProfesorByFeign(@PathVariable("id")String id);
}
