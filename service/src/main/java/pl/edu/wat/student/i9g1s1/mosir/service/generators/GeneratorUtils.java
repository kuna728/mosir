package pl.edu.wat.student.i9g1s1.mosir.service.generators;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsMembershipCard;
import pl.edu.wat.student.i9g1s1.mosir.domain.ClientsTicket;
import pl.edu.wat.student.i9g1s1.mosir.domain.DiscountType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Base64;

@Component
public class GeneratorUtils {

    public byte[] generatePDFFromTemplate(String templateName, Context context) throws DocumentException, IOException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(generateHtmlFromTemplate(templateName, context));
        renderer.getFontResolver().addFont("Roboto-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        ByteArrayOutputStream bytesOutput = new ByteArrayOutputStream();
        renderer.layout();
        renderer.createPDF(bytesOutput);
        return bytesOutput.toByteArray();
    }

    public String generateHtmlFromTemplate(String templateName, Context context) throws IOException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine.process(templateName, context);
    }

    public String getTotalAmount(ClientsTicket clientsTicket) {
        return getFormattedStringFromBigDecimal(
                clientsTicket.getTicket().getPrice().multiply(clientsTicket.getDiscountType().getValue())
        );
    }

    public String getTotalAmount(ClientsMembershipCard clientsMembershipCard) {
        return getFormattedStringFromBigDecimal(
                clientsMembershipCard.getMembershipCard().getPrice().multiply(clientsMembershipCard.getDiscountType().getValue())
        );
    }

    public String getFormattedStringFromBigDecimal(BigDecimal bigDecimal) {
        bigDecimal.setScale(2, RoundingMode.DOWN);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(0);
        decimalFormat.setGroupingUsed(false);
        return decimalFormat.format(bigDecimal);
    }

    public String getDiscountTypeName(DiscountType discountType) {
        return discountType.getName() + " (" + getFormattedStringFromBigDecimal(discountType.getValue().multiply(BigDecimal.valueOf(100))) + "%)";
    }

    public String getEncodedBanner() throws IOException {
        return Base64.getEncoder().encodeToString(
                IOUtils.toByteArray(new ClassPathResource("static/images/common/banner.jpg").getInputStream())
        );
    }
}
