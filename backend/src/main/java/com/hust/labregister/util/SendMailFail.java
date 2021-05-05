package com.hust.labregister.util;

import com.hust.labregister.model.*;

public class SendMailFail {

    private static final String SUBJECT = "[FAILED] BKLab thông báo việc đăng kí phòng lab không thành công";
    private static final String CONTENT = "Xin chào %username% !\n" +
                                    "Chúng tôi rất tiếc phải thông báo rằng chúng tôi không thể tìm phòng lab phù hợp cho đăng kí của bạn!\n" +
                                    "Thông tin đăng kí:\n" +
                                    "    + Ngày đăng kí: %createdAt%\n" +
                                    "    + Loại phòng: %roomType%\n" +
                                    "    + Số lượng người: %groupSize%\n" +
                                    "    + Ngày mong muốn: %date%\n" +
                                    "    + Thời gian bắt đầu: %startTime%\n" +
                                    "    + Thời gian kết thúc: %endTime%\n" +
                                    "    + Thiết bị đăng kí: %equipment%\n" +
                                    "    + Số lượng thiết bị: %numOfEquiment%\n" +
                                    "    \n" +
                                    "Bạn có thể thực hiện đăng kí lại cho tuần sau !\n" +
                                    "\n" +
                                    "BKLAB";

    public static void send(User user, RoomRegistration registration){
        String text = CONTENT;
        text = text.replace("%username%", user.getEmail());
        text = text.replace("%createdAt%", StringUtils.formatDate(registration.getCreatedAt()));
        text = text.replace("%roomType%", String.valueOf(registration.getRoomType()));
        text = text.replace("%groupSize%", String.valueOf(registration.getGroupSize()));
        text = text.replace("%date%", StringUtils.formatDate(registration.getDate()));
        text = text.replace("%startTime%", String.valueOf(registration.getStartTime())+" h");
        text = text.replace("%endTime%", String.valueOf(registration.getEndTime())+" h");
        if(registration.getEquipmentRegistration() != null && registration.getEquipmentRegistration().size() > 0){
            EquipmentRegistration equipmentRegistration = registration.getEquipmentRegistration().get(0);
            text = text.replace("%equipment%", equipmentRegistration.getEquipment().getName());
            text = text.replace("%numOfEquiment%", String.valueOf(equipmentRegistration.getAmount()));
        }else{
            text = text.replace("%equipment%", "");
            text = text.replace("%numOfEquiment%", String.valueOf(0));
        }
        String title = SUBJECT;
        MailUtils.send(user.getEmail(), text, title);
    }
}
