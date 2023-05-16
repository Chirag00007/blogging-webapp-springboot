package com.chirag.main.dto;

import com.chirag.main.entities.Category;
import com.chirag.main.entities.Comment;
import com.chirag.main.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {


    private Integer pId;
    private String pTitle;

    private String pContent;

    private String pPic;

    private Date pDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
