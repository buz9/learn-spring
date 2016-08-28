/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;

/**
 *  Author : tungtt         
 * Aug 22, 2016
 */
@Controller
public class StudentController {
	@Autowired
	private StudentDAO studentDAO;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="student/add", method=RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("student.form", "command", new Student());
	}
	
//	@RequestMapping(value="student/save", method=RequestMethod.POST)
//	public ModelAndView save(@RequestParam(value="name", required=false) String name,
//			@RequestParam(value="age", required=false) Integer age) {
//		ModelAndView mv = new ModelAndView("student.view");
//		Student student = new Student(name, age);
////		mv.addObject("name", name);
////		mv.addObject("age", age);
//		mv.addObject("student", student);
//		return mv;
//	}
	
	@RequestMapping(value="student/save", method=RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
		ModelAndView mv;
		if(result.hasErrors()) {
			mv = new ModelAndView("student.form", "command", student);
			mv.addObject("errors", result);
			return mv;
		}
		if(student.getId() > 0) {
			studentDAO.update(student);
		} else {
			studentDAO.insert(student);
		}
		mv = new ModelAndView("redirect:/student/list");
		return mv;
	}
	
	@RequestMapping(value="student/edit/save", method=RequestMethod.POST)
	public String saveEdit() {
		return "forward:/student/save";
	}
	
	@RequestMapping(value="student/list")
	public ModelAndView listStudent(@RequestParam(value="q", required=false) String query) {
		ModelAndView mv = new ModelAndView("StudentList");
		if(query == null || query.isEmpty()) query = "";
		mv.addObject("students", studentDAO.list(query));
		
		return mv;
	}
	
	@RequestMapping(value="student/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id) {
		studentDAO.delete(id);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="student/edit")
	public ModelAndView edit(@RequestParam("id") Integer id) {
		Student student = studentDAO.getById(id);
		return new ModelAndView("student.form", "command", student);
	}
	
	@RequestMapping(value="student/json/{id}", method=RequestMethod.GET)
	public @ResponseBody Student viewJson(@PathVariable(value="id") Integer id) {
		return studentDAO.getById(id);
	}
	
	@RequestMapping(value="student/avatar/save", method=RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file, @RequestParam(value="id") Integer id, HttpServletRequest request) throws IOException {
		if(file.isEmpty()) return "student.error";
		Path avatarFile = getImageFile(request, id);
		Files.write(avatarFile, file.getBytes(), StandardOpenOption.CREATE);
		
		System.out.println(avatarFile);
		return "redirect:/student/list";
	}
	
	private Path getImageFile(HttpServletRequest request, Integer id) {
		ServletContext servletContext = request.getSession().getServletContext();
		String diskPath = servletContext.getRealPath("/");
		File folder = new File(diskPath + File.separator + "avatar" + File.separator);
		folder.mkdirs();
		return new File(folder, String.valueOf(id) + ".jpg").toPath();
	}
	
	@RequestMapping(value="student/avatar/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable(value="id") Integer id, HttpServletRequest request) throws IOException {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10000);
		if(id != null) {
			Path avatarFile = getImageFile(request, id);
			if(Files.exists(avatarFile)) byteOutput.write(Files.readAllBytes(avatarFile));
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(byteOutput.toByteArray(), httpHeaders, HttpStatus.CREATED);
	}
}

