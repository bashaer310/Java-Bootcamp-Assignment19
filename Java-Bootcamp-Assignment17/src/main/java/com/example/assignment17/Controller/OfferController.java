package com.example.assignment17.Controller;

import com.example.assignment17.Api.ApiResponse;
import com.example.assignment17.Model.Offer;
import com.example.assignment17.Model.Project;
import com.example.assignment17.Model.User;
import com.example.assignment17.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer")
public class OfferController {
    private final OfferService offerService;


    @GetMapping("/get")
    public ResponseEntity getOffers(){
        return  ResponseEntity.status(200).body(offerService.get());
    }

    @PostMapping("/add/{id}")
    public ResponseEntity addOffer(@AuthenticationPrincipal User user, @RequestBody @Valid Offer offer,@PathVariable Integer projectId){
        offerService.add(offer,projectId,user);
        return ResponseEntity.status(200).body(new ApiResponse("Offer added"));
    }
    @PutMapping("/update/{projectId}/{offerId}")
    public ResponseEntity updateOffer(@AuthenticationPrincipal User user, @RequestBody @Valid Offer offer,@PathVariable Integer projectId,@PathVariable Integer offerId){
        offerService.update(offer,user,projectId,offerId);
        return ResponseEntity.status(200).body(new ApiResponse("Offer updated"));
    }
    @DeleteMapping("/delete/{projectId}/{offerId}")
    public ResponseEntity deleteOffer(@AuthenticationPrincipal User user,@PathVariable Integer projectId,@PathVariable Integer offerId){
        offerService.delete(user,projectId,offerId);
        return ResponseEntity.status(200).body(new ApiResponse("Offer deleted"));
    }

    @GetMapping("/getByProjectId/{projectId}")
    public ResponseEntity getOfferByProjectId(@PathVariable Integer projectId){
        return  ResponseEntity.status(200).body(offerService.getByProjectId(projectId));
    }
    @GetMapping("/getByExpertId/{expertId}")
    public ResponseEntity getOfferByExpertId(@PathVariable Integer expertId){
        return  ResponseEntity.status(200).body(offerService.getByExpertId(expertId));
    }
    @GetMapping("/getByProjectIdAndPrice/{projectId}/{price}")
    public ResponseEntity getOfferByProjectIdAndPrice(@PathVariable Integer projectId,@PathVariable Integer price){
        return  ResponseEntity.status(200).body(offerService.getByProjectIdAndPrice(projectId,price));
    }
}
