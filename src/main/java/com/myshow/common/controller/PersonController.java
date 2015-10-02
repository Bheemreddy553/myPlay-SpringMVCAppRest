package com.myshow.common.controller;



import com.reddy.my_show.server.Person;
import com.reddy.my_show.server.dao.PersonDAO;
import com.reddy.my_show.server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }



    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public @ResponseBody List<Person> listPersons() {
       List<Person> persons = this.personService.listPersons();
        return persons;
    }

    //For add and update person both
    @RequestMapping(value= "/person/add", method = RequestMethod.POST)
    public String addPerson(@RequestBody Person p){

        if(p.getId() == 0){
            //new person, add it
            this.personService.addPerson(p);
        }else{
            //existing person, call update
            this.personService.updatePerson(p);
        }

        return "redirect:/persons";

    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.personService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

}