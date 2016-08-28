/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.utils.XSLTUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *  Author : tungtt         
 * Aug 26, 2016
 */
@Controller
public class ClazzController {
	@Autowired
	private StudentDAO studentDAO;
	
	@RequestMapping(value="/clazz/xml", produces={MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody JavaClazz viewInXML() {
		return new JavaClazz(studentDAO.list());
	}
	
	@RequestMapping(value="/clazz/json", produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody JavaClazz viewInJSON() {
		return new JavaClazz(studentDAO.list());
	}
	
	@RequestMapping(value="/clazz/xslt")
	public ModelAndView viewXSLT() throws JAXBException, ParserConfigurationException, SAXException, IOException {
		JavaClazz clazz = new JavaClazz(studentDAO.list());
		ModelAndView model = new ModelAndView("ClazzView");
		model.getModelMap().put("data", XSLTUtils.clazzToDomSource(clazz));
		return model;
	}
	
	@RequestMapping(value="/clazz/excel")
	public ModelAndView viewExcel() {
		JavaClazz clazz = new JavaClazz(studentDAO.list());
		ModelAndView model = new ModelAndView("excelView");
		model.getModelMap().put("data", clazz);
		return model;
	}
	
	@RequestMapping(value="/clazz/pdf", produces="application/pdf")
	public ModelAndView viewPdf() {
		JavaClazz clazz = new JavaClazz(studentDAO.list());
		ModelAndView model = new ModelAndView("pdfView");
		model.getModelMap().put("clazzObj", clazz);
		return model;
	}
	
	@RequestMapping(value="/clazz/report", produces="application/pdf")
	public ModelAndView viewReport() {
		JavaClazz clazz = new JavaClazz(studentDAO.list());
		JRDataSource dataSource = new JRBeanCollectionDataSource(clazz.getStudents());
		ModelAndView model = new ModelAndView("pdfReport");
		model.getModelMap().put("dataSource", dataSource);
		return model;
	}
}
