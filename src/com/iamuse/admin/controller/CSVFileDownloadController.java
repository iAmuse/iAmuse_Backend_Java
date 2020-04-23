package com.iamuse.admin.controller;
 
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.iamuse.admin.VO.ImageEmailFormVO;
import com.iamuse.admin.service.IamuseDashboardService;
import com.iamuse.admin.util.IAmuseadminUtil;
 
/**
 * This Spring controller class implements a CSV file download functionality.
 * @author www.codejava.net
 *
 */
@Controller
public class CSVFileDownloadController {
	
	@Autowired
	MessageSource messageSource;
	@Autowired
	IamuseDashboardService iamuseDashboardService;
	
	private Locale locale = LocaleContextHolder.getLocale();
    @RequestMapping(value = "/downloadCSV")
    public String downloadCSV(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException {
 
        String csvFileName = "UserEmails.csv";
 
        response.setContentType("text/csv");
 
        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
         String dateFrom=IAmuseadminUtil.convertDateDDMMYYYToYYYMMDD(request.getParameter("startDate"));
         String dateTo=IAmuseadminUtil.convertDateDDMMYYYToYYYMMDD(request.getParameter("endDate"));
         System.out.println(dateFrom);
         System.out.println(dateTo);
         
         String userID=""+request.getSession().getAttribute("userId");
         int userId=Integer.parseInt(userID);
        List<ImageEmailFormVO> emailImagesList = iamuseDashboardService.getUserEmailListForExport(dateFrom,dateTo,userId);
        // uses the Super CSV API to generate CSV data from the model data
        if(emailImagesList.size()==0)
        {
        redirectAttributes.addFlashAttribute("errorMessage",  messageSource.getMessage("csv.empty.record",null,locale));
        return "redirect:iamuseEmailImagesList";
        }
        else{
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
 
        String[] header = { "serialNumber","emailId","uploadTime","mailSentTime" };
 
        csvWriter.writeHeader(header);
 
        for (ImageEmailFormVO emailList : emailImagesList) {
            csvWriter.write(emailList, header);
        }
        
        csvWriter.close();
        }
        return "emailimages";
    }
    
    
    @RequestMapping(value="/clearList")
  	public String clearList(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap,RedirectAttributes redirectAttributes)
 	{  
	     String userId=""+request.getSession().getAttribute("userId");
 		 boolean result=false;
 		result=iamuseDashboardService.clearList(userId);
 		if(result==true){
 			redirectAttributes.addFlashAttribute("successMessage",  messageSource.getMessage("img.clear.success",null,locale));
 			return "redirect:iamuseEmailImagesList";
 		}
 		if(result==false){
 			redirectAttributes.addFlashAttribute("errorMessage",  messageSource.getMessage("img.clear.already",null,locale));
 			return "redirect:iamuseEmailImagesList";
 		}
 	System.out.println("false>>>>>>>>>>>>>>>>>>>>");
 		return "redirect:iamuseEmailImagesList";
 	}
}