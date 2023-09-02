package com.example.assignment17.Repository;

import com.example.assignment17.Model.Expert;
import com.example.assignment17.Model.Offer;
import com.example.assignment17.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    Offer findOfferById(Integer id);
    Offer findOfferByIdAndExpertAndProject(Integer id,Expert expert,Project project);
    List<Offer> findOfferByProject(Project project);
    List<Offer> findOfferByProjectAndMinPriceLessThanEqual(Project project, Integer price);
    List<Offer> findOfferByExpert(Expert expert);
}
