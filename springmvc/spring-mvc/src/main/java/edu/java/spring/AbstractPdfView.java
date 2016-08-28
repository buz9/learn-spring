/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Author : tungtt Aug 26, 2016
 */
public abstract class AbstractPdfView extends AbstractView {

	public AbstractPdfView() {
		this.setContentType("application/pdf");
	}

	@Override
	protected boolean generatesDownloadContent() {
		return false;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ByteArrayOutputStream byteOutput = createTemporaryOutputStream();

		Document document = new Document();
		PdfWriter writer;
		writer = newWriter(document, byteOutput);
		prepareWriter(model, writer, request);
		document.open();
		buildPdfDocument(model, request, document, writer, response);
		document.close();
		writeToResponse(response, byteOutput);
	}

	protected abstract void buildPdfDocument(Map<String, Object> model, HttpServletRequest request, Document document,
			PdfWriter writer, HttpServletResponse response) throws Exception;

	protected Document newDocument() {
		return new Document(PageSize.A4);
	}

	protected PdfWriter newWriter(Document document, ByteArrayOutputStream byteOutput) throws DocumentException {
		return PdfWriter.getInstance(document, byteOutput);
	}

	protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request) {
		writer.setViewerPreferences(getViewerPreferences());
	}

	protected int getViewerPreferences() {
		return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
	}

}
