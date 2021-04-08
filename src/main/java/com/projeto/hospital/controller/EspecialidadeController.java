package com.projeto.hospital.controller;

import com.projeto.hospital.dao.EspecialidadeDAO;
import com.projeto.hospital.models.Especialidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadeController {
    @RequestMapping(value = "/novoEspecialidade", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("especialidade", new Especialidade());
        return "novoEspecialidade";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvarMedico(@ModelAttribute Especialidade especialidade) throws SQLException {
        new EspecialidadeDAO().insert(especialidade);
        return "redirect:/especialidades";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listarEspecialidades(Model model) throws SQLException {
        model.addAttribute("especialidades", new EspecialidadeDAO().findAll());
        return "especialidades";
    }
}
