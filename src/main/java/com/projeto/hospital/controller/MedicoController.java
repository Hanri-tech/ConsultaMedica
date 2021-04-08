package com.projeto.hospital.controller;

import com.projeto.hospital.dao.EspecialidadeDAO;
import com.projeto.hospital.dao.MedicoDAO;
import com.projeto.hospital.models.Especialidade;
import com.projeto.hospital.models.Medico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @RequestMapping(value = "/novoMedico", method = RequestMethod.GET)
    public String add(Model model) throws SQLException {
        model.addAttribute("medico", new Medico());
        model.addAttribute("especialidades", new EspecialidadeDAO().findAll());
        return "novoMedico";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvarMedico(@ModelAttribute Medico medico) throws SQLException {
        new MedicoDAO().insert(medico);
        return "redirect:/medicos";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listarMedicos(Model model) throws SQLException {
        model.addAttribute("medicos", new MedicoDAO().findAll());
        return "medicos";
    }
}
