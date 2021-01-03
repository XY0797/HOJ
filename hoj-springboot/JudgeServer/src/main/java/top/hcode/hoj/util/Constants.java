package top.hcode.hoj.util;

/**
 * @Author: Himit_ZH
 * @Date: 2021/1/1 13:00
 * @Description: 常量枚举类
 */
public class Constants {
    /**
     * @Description 提交评测结果的状态码
     * @Since 2021/1/1
     */
    public enum Judge{
        STATUS_NOT_SUBMITTED (-10,"Not Submitted"),
        STATUS_PRESENTATION_ERROR (-3,"Presentation Error"),
        STATUS_COMPILE_ERROR (-2,"Compile Error"),
        STATUS_WRONG_ANSWER (-1,"Wrong Answer"),
        STATUS_ACCEPTED (0,"Accepted"),
        STATUS_CPU_TIME_LIMIT_EXCEEDED (1,"CPU Time Limit Exceeded"),
        STATUS_REAL_TIME_LIMIT_EXCEEDED (2,"Real Time Limit Exceeded"),
        STATUS_MEMORY_LIMIT_EXCEEDED (3,"Memory Limit Exceeded"),
        STATUS_RUNTIME_ERROR (4,"Runtime Error"),
        STATUS_SYSTEM_ERROR (5,"System Error"),
        STATUS_PENDING (6,"Pending"),
        STATUS_JUDGING (7,"Judging"),
        STATUS_PARTIAL_ACCEPTED (8,"Partial Accepted"),
        STATUS_SUBMITTING (9,"Submitting"),
        STATUS_NULL (10,"No Status");

        private final Integer status;
        private final String name;

        private Judge(Integer status, String name) {
            this.status = status;
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public static Judge getTypeByStatus(int status){
            for (Judge judge:Judge.values()){
                if (judge.getStatus()==status){
                    return judge;
                }
            }
            return STATUS_NULL;
        }
    }
}