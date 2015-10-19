package com.gymworkout.example.controller;

import com.gymworkout.example.model.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pfranca on 9/28/2015.
 */

@Controller("nameController")
@RequestMapping(value = "/name")
public class NameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NameController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public final List<Name> get() {
        LOGGER.debug("Getting all names");
        List<Name> found = Arrays.asList(Name.values());
        LOGGER.debug("Found names");
        return found;
    }
}
