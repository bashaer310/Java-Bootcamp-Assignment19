package com.example.assignment17.Service;

import com.example.assignment17.Api.ApiException;
import com.example.assignment17.Model.*;
import com.example.assignment17.Repository.AuthRepository;
import com.example.assignment17.Repository.ExpertRepository;
import com.example.assignment17.Repository.OfferRepository;
import com.example.assignment17.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final AuthRepository authRepository;
    private final ExpertRepository expertRepository;
    private final ProjectRepository projectRepository;
    public List<Offer> get(){
        return offerRepository.findAll();
    }


    public void add(Offer offer, Integer projectId, User user){
        User user1=authRepository.findUserById(user.getId());
        if(user1==null)
            throw new ApiException("User id not found");
        Expert expert=expertRepository.findExpertByUser(user1);
        if(expert==null)
            throw new ApiException("Expert not found");
        Project project=projectRepository.findProjectById(projectId);
        if(project==null)
            throw new ApiException("Project not found");

        offer.setProject(project);
        offer.setExpert(expert);
        offer.setCreatedAt(LocalDate.now());
        offerRepository.save(offer);
    }


    public void update(Offer offer, User user, Integer projectId,Integer offerId){
        Expert expert=expertRepository.findExpertByUser(user);
        if(expert==null)
            throw new ApiException("Expert details not found");

        Project project=projectRepository.findProjectById(projectId);
        if(project==null)
            throw new ApiException("Project not found");

        Offer oldOffer=offerRepository.findOfferByIdAndExpertAndProject(offerId,expert,project);
        if(oldOffer==null)
            throw new ApiException("Offer not found");

        oldOffer.setOffer(offer.getOffer());
        oldOffer.setMinPrice(offer.getMinPrice());
        offerRepository.save(oldOffer);
    }

    public void delete(User user, Integer projectId, Integer offerId){
        Expert expert=expertRepository.findExpertByUser(user);
        if(expert==null)
            throw new ApiException("Expert details not found");

        Project project=projectRepository.findProjectById(projectId);
        if(project==null)
            throw new ApiException("Project not found");

        Offer oldOffer=offerRepository.findOfferByIdAndExpertAndProject(offerId,expert,project);
        if(oldOffer==null)
            throw new ApiException("Offer not found");

        offerRepository.delete(oldOffer);
    }

    public List<Offer> getByProjectId(Integer projectId){
        Project project=projectRepository.findProjectById(projectId);
        if(project==null)
            throw new ApiException("Project not found");
        List<Offer> offers = offerRepository.findOfferByProject(project);
        if(offers==null)
            throw new ApiException("Offers not found");

        return offers;
    }

    public List<Offer> getByProjectIdAndPrice(Integer projectId,Integer price){
        Project project=projectRepository.findProjectById(projectId);
        if(project==null)
            throw new ApiException("Project not found");
        List<Offer> offers = offerRepository.findOfferByProjectAndMinPriceLessThanEqual(project,price);
        if(offers==null)
            throw new ApiException("Offers not found");

        return offers;
    }

    public List<Offer> getByExpertId(Integer expertId){
        User user=authRepository.findUserById(expertId);
        if(user==null)
            throw new ApiException("User not found");

        Expert expert=expertRepository.findExpertByUser(user);
        if(expert==null)
            throw new ApiException("Expert details not found");

        List<Offer> offers = offerRepository.findOfferByExpert(expert);
        if(offers==null)
            throw new ApiException("Offers not found");

        return offers;
    }


}
