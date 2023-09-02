package com.example.assignment17.Controller;


import com.example.assignment17.Api.ApiResponse;
import com.example.assignment17.DTO.CustomerDTO;
import com.example.assignment17.DTO.ExpertDTO;
import com.example.assignment17.Model.User;
import com.example.assignment17.Service.ExpertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expert")
public class ExpertController {
    private final ExpertService expertService;


    @GetMapping("/get")
    public ResponseEntity getExperts(){
        return ResponseEntity.status(200).body(expertService.getAll());
    }



    @PostMapping("/add")
    public ResponseEntity addExpert(@AuthenticationPrincipal User user, @RequestBody @Valid ExpertDTO expert){
        expertService.add(expert,user);
        return ResponseEntity.status(200).body(new ApiResponse("Expert details added"));
    }


    @PutMapping("/update")
    public ResponseEntity updateExpert(@AuthenticationPrincipal User user, @RequestBody @Valid ExpertDTO expert) {
        expertService.update(expert,user);
        return ResponseEntity.status(200).body(new ApiResponse("Expert details updated"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteExpert(@AuthenticationPrincipal User user) {
        expertService.delete(user);
        return ResponseEntity.status(200).body(new ApiResponse("Expert details deleted"));
    }


    @GetMapping("/getByUsername/{username}")
    public ResponseEntity getExpertByUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(expertService.getByUsername(username));
    }

    @GetMapping("/getByMajor/{major}")
    public ResponseEntity getExpertByMajor(@PathVariable String major){
        return  ResponseEntity.status(200).body(expertService.getByMajor(major));
    }

}
