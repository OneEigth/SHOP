package oneeight.shop.notification;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderNotificationService {

    private final JavaMailSender mailSender;

    public void sendOrderNotification(Order order) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(order.getAppUser().getEmail());
        mail.setSubject("Order status has been updated");
        mail.setText("Dear " + order.getAppUser().getName() + ",\n\nYour order #" + order.getId()+
                " has been updated to " + order.getStatus().getStatus() + ".\n\nThank you for using our service.");
        mailSender.send(mail);
    }
}
