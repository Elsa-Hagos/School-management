package et.com.gebeya.Asquala.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class StudentSubjectResponse {
        private Integer id;
        private String name;
    }


