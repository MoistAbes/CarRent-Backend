package com.example.rentcarbackend.client;

import com.example.rentcarbackend.dto.CarReviewDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class WebSearchClientTestSuite {


    @Autowired
    WebSearchClient webSearchClient;

    @Test
    void shouldGetEditedReview(){

        //Given
        CarReviewDto review = webSearchClient.getReview("BMW 2019 REVIEW");

        //When
        String editedReview = review.getBody();



        //Then
        assertFalse(editedReview.contains("\\"));
    }



    public String editReview(String body){


        String removeBeg = body.substring(9);
        String removeEnd = removeBeg.substring(0, removeBeg.length() - 2);

        char[] test = removeEnd.toCharArray();

        for (int i = 0; i < test.length; i++){
            if (test[i] == 92){
                test[i] = '\n';
                test[i + 1] = ' ';
            }
        }

        return String.valueOf(test);

    }
}
