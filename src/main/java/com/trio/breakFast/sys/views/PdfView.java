package com.trio.breakFast.sys.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.trio.breakFast.model.Questionnaire;
import com.trio.breakFast.util.PdfUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author loser
 * @ClassName PdfView
 * @Description
 * @Date 2016/05/30 20:36
 */
@Component
public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        PdfProduce.createAgreement((String)model.get(PdfProduce.TEMPLATE_CONTENT_KEY),(Map<String, String>)model.get(PdfProduce.PLACEHOLDER_MAP_KEY),document);
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode((String) model.get(PdfUtil.QUESTIONNAIRE_FILE_NAME)+".pdf", "UTF-8"));
        writer.setStrictImageSequence(true);
        PdfUtil.createPDF((Questionnaire) model.get(PdfUtil.QUESTIONNAIRE_KEY), (String) model.get(PdfUtil.QUESTIONNAIRE_PICTURE_PATH), document);
    }
}
