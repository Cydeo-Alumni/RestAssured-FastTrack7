package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Cluster {

    private int id;
    private String name;
    @JsonProperty("rooms")
    private List<Room> roomList;
}
