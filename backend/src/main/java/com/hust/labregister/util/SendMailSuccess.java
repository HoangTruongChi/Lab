package com.hust.labregister.util;

import com.hust.labregister.model.EquipmentDivisionResult;
import com.hust.labregister.model.RoomDivisionResults;
import com.hust.labregister.model.User;

public class SendMailSuccess {

    private static final String SUBJECT = "[SUCCESS] BKLab thông báo kết quả đăng kí phòng lab";
    private static final String CONTENT = "Xin chào %username% !\n" +
                                    "Chúc mừng bạn đã đăng kí sử dụng thành công phòng lab tại BKLab System.\n" +
                                    "Chúng tôi xin thông báo đến bạn kết quả đăng kí phòng như sau:\n" +
                                    "Kết qủa đăng kí:\n" +
                                    "    + Ngày sử dụng: %date%\n" +
                                    "    + Tên phòng: %roomName%\n" +
                                    "    + Mã phòng: %roomCode%\n" +
                                    "    + Loại phòng: %roomType%\n" +
                                    "    + Số lượng người: %groupSize%\n" +
                                    "    + Thời gian bắt đầu: %startTime%\n" +
                                    "    + Thời gian kết thúc: %endTime%\n" +
                                    "    + Thiết bị đăng kí: %equipment%\n" +
                                    "    + Số lượng thiết bị: %numOfEquiment%\n" +
                                    "\n" +
                                    "Hẹn gặp lại bạn tại BKLab !    \n" +
                                    "\n" +
                                    "BKLAB";

    public static void send(RoomDivisionResults results, User user){
        String text = CONTENT;
        text = text.replace("%username%", user.getEmail());
        text = text.replace("%date%", StringUtils.formatDate(results.getDate()));
        text = text.replace("%roomName%", results.getRoom().getName());
        text = text.replace("%roomCode%", results.getRoom().getCode());
        text = text.replace("%roomType%", String.valueOf(results.getRoom().getRoomType()));
        text = text.replace("%groupSize%", String.valueOf(results.getGroupSize()));
        text = text.replace("%startTime%", String.valueOf(results.getStartTime())+" h");
        text = text.replace("%endTime%", String.valueOf(results.getEndTime())+" h");
        if(results.getEquipmentDivisionResults() != null && results.getEquipmentDivisionResults().size() > 0){
            EquipmentDivisionResult equipmentDivisionResult = results.getEquipmentDivisionResults().get(0);
            text = text.replace("%equipment%", equipmentDivisionResult.getEquipment().getName());
            text = text.replace("%numOfEquiment%", String.valueOf(equipmentDivisionResult.getAmount()));
        }else{
            text = text.replace("%equipment%", "");
            text = text.replace("%numOfEquiment%", String.valueOf(0));
        }
        String title = SUBJECT;
        MailUtils.send(user.getEmail(), text, title);
    }
}
