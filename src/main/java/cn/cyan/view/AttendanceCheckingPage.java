package cn.cyan.view;

import cn.cyan.facedao.FacebaiduDeal;
import cn.cyan.util.DB;
import cn.cyan.util.FileUtil;
import com.baidu.aip.util.Base64Util;
import com.github.sarxos.webcam.*;
import com.github.sarxos.webcam.util.ImageUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: Cyan
 * @Date: 2019/5/20 8:12
 * borderlayout GridBagLayout 搞不定呐
 * 下一步计划暑假攻克
 */
public class AttendanceCheckingPage extends JFrame {

    private final Webcam webcam = Webcam.getDefault();
    private WebcamPanel webcamPanel;
    private JFrame jFrameAttendanceChecking;
//    private JButton jButtonTakingPhoto;
    /**人脸考勤按钮
     * 即人脸识别出是谁 并且将信息展示再头部提示行
     * 考勤成功即人脸搜索成功 并再头部提示行 xxx同学你好 请选择签到/签退
     * （签到与签退按钮只能一次按一个  有一个在同一时刻是不能按的 ）
     * 按了签到 或者 签退 按钮以后
     * 在头部提示行显示
     * xxx同学签到成功
     */
    private JButton jButtonRecognize; //考勤按钮 可以加入键盘快捷键 如空格
    private JButton jButtonSignIn;   //签到按钮 Ctrl
    private JButton jButtonSignBack;  //签退按钮 Alt
    private JTextField jTextFieldInfo; //头部提示行
    private JPanel jPanelBtns;  //底部三个按钮（暂定）
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;

    public AttendanceCheckingPage() {

        //webcam 即摄像头
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        //webcampanel是webcam封装好的
        webcamPanel = new WebcamPanel(webcam);
//        jFrameAttendanceChecking = new JFrame("学生考勤界面");
        jTextFieldInfo = new JTextField();
//        jButtonTakingPhoto = new JButton("拍照");
        jButtonRecognize = new JButton("考勤");
        jButtonSignIn = new JButton("签到");
        jButtonSignBack = new JButton("签退");


        this.setTitle("学生考勤界面");
//        this.setLocationRelativeTo(null);
        //修改左上角Java图标
        URL imgURL = this.getClass().getResource("/viewingImg/titleicon.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);
        Image image = imageIcon.getImage();
        this.setIconImage(image);
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch(Exception e) {
            //TODO exception
            e.printStackTrace();
        }

        /**
         * 一开始不能点击
         * 考勤成功才能点击
         */
        jButtonSignIn.setEnabled(false);
        jButtonSignBack.setEnabled(false);

        /**网包布局**/
//        gridBagLayout = new GridBagLayout();  //实例化布局对象
//        jPanelBtns = new JPanel(); //面板设置为GridBagLayout布局
//        jPanelBtns.setLayout(new BorderLayout());
//        jPanelBtns.setPreferredSize(new Dimension(300, 100));

//        gridBagConstraints = new GridBagConstraints(); //实例化这个对象用来对组件进行管理
//        gridBagConstraints.fill = GridBagConstraints.BOTH;  //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        /**NONE: 不调整组件大小
         * HORIZONTAL: 加宽组件 使他在水平方向上填满其显示区域 但是不改变高度
         * VERTICAL： 加高组件 使他在垂直方向上填满其显示区域 但是不改变宽度
         * BOTH: 使组件完全填满其显示区域**/

        /**
         * 头部提示行的设置
         */
        jTextFieldInfo.setBorder(new EmptyBorder(0, 0, 0, 0));
        jTextFieldInfo.setPreferredSize(new Dimension(0, 36));  //https://blog.csdn.net/isea533/article/details/8593183 setPreferredSize()方法
        jTextFieldInfo.setEditable(false);
        jTextFieldInfo.setText("这里是提示框！");
        jTextFieldInfo.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        /**
         * webcamPanel的设置
         */
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);

        /**
         * 对jpanelBtns 的布局设置
         * 分别对组件进行设置
         * 考勤按钮在第一行
         * 签到 签退按钮并排在第二行
         *  //组件1(gridx,gridy)组件的左上角坐标，
         *  gridwidth，gridheight：组件占用的网格行数和列数
         */
//        //考勤按钮
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.gridwidth = 8;
//        gridBagConstraints.gridheight = 2;
//        gridBagLayout.setConstraints(jButtonRecognize, gridBagConstraints);
//
//        //签到按钮
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.gridwidth = 4;
//        gridBagConstraints.gridheight = 2;
//        gridBagLayout.setConstraints(jButtonSignIn, gridBagConstraints);
//
//        //签退按钮
//        gridBagConstraints.gridx = 4;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.gridwidth = 4;
//        gridBagConstraints.gridheight = 1;
//        gridBagLayout.setConstraints(jButtonSignBack, gridBagConstraints);

//        jPanelBtns.add(jButtonRecognize);
//        jPanelBtns.add(jButtonSignIn);
//        jPanelBtns.add(jButtonSignBack);

        /**
         * 将按钮组设置为边界布局
         * 网格组布局不成功
         */
//        jButtonRecognize.setPreferredSize(new Dimension(100, 50));
//        jButtonSignIn.setPreferredSize(new Dimension(50, 50));
//        jButtonSignBack.setPreferredSize(new Dimension(50, 50));

//        jPanelBtns.add(jButtonRecognize, BorderLayout.NORTH);
//        jPanelBtns.add(jButtonSignIn, BorderLayout.CENTER);
//        jPanelBtns.add(jButtonSignBack, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(jTextFieldInfo, BorderLayout.NORTH);
        this.add(webcamPanel, BorderLayout.CENTER);
//        this.add(jTextFieldInfo, BorderLayout.NORTH);
//        this.add(webcamPanel, BorderLayout.CENTER);
//        this.add(jPanelBtns, BorderLayout.SOUTH);
        this.setBounds(0, 0, 880, 720);
        this.setResizable(true);
//        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
//        jFrameAttendanceChecking.add(jTextFieldInfo, BorderLayout.NORTH);
//        jFrameAttendanceChecking.add(webcamPanel, BorderLayout.CENTER);
//        jFrameAttendanceChecking.add(jPanelBtns, BorderLayout.SOUTH);
//        jFrameAttendanceChecking.setResizable(true);
//        jFrameAttendanceChecking.pack();
//        jFrameAttendanceChecking.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        jFrameAttendanceChecking.setVisible(true);
//        jFrameAttendanceChecking.setLocationRelativeTo(null);



        /**
         *
         */
//        jButtonRecognize.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                jButtonRecognize.setEnabled(false);  //设置按钮不可点击
//
//
//                //实现拍照保存-------start
//                //相对路径和绝对路径
//                //LocalFaceDataBase
////                final String fileName = "D://Java大作业//FaceRecoDemo//LocalFaceDataBase//" + System.currentTimeMillis();       //保存路径即图片名称（不用加后缀）
//                final String fileName = "D://Java大作业//FaceRecoDemo//out//artifacts//FaceRecoDemo_jar//LocalDataBase/" + System.currentTimeMillis();       //保存路径即图片名称（不用加后缀）
////D:\Java大作业\FaceRecoDemo\src\main\java\cn\cyan\view\LocalFaceDataBase
//                /**
//                 * 注意！！！！！！！！！
//                 * 修改为jpg 和加后缀
//                 */
//                WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_JPG);
//                SwingUtilities.invokeLater(new Runnable() {
//
//                    @Override
//                    public void run() {
////                        JOptionPane.showMessageDialog(null, "拍照成功");
//                        jButtonRecognize.setEnabled(true);    //设置按钮可点击
//
//                        /**
//                         * 使用api调用接口搜索人脸（人脸识别）考勤
//                         */
//                        try {
//                            String filename = fileName + ".jpg";
//                            System.out.println("filename is " + filename);
//
//                            byte[] imgData;
//                            imgData = FileUtil.readFileByBytes(filename);
//                            String imgStr = Base64Util.encode(imgData);
//                            if (imgStr == null) {
//                                throw new Exception("警告：人脸图像数据转换失败为空！");
//                            }
//
//                            String imgParam = null;
//                            imgParam = URLEncoder.encode(imgStr, "UTF-8"); //base64 编码
//
//                            FacebaiduDeal facedeal = new FacebaiduDeal();
//                            /**
//                             * 核心人脸识别接口 facesearch()
//                             */
//                            JSONObject jsonjt = facedeal.facesearch(imgParam);
//
//                            /**
//                             * 开始解析JSON
//                             * 根据返回错误码来判断是否人脸搜索成功
//                             * 但是是否有这个人 还需从score来判断
//                             */
//                            String error_msg = jsonjt.optString("error_msg");
//                            String error_code = jsonjt.optString("error_code");
//
//                            System.out.println("errormsg:"+error_msg);
//
//                            if(error_code.equals("0")){
//
//                                /**
//                                 * 人脸搜索成功 即考勤成功 至于签到还是签退 按钮能否点击提示用户
//                                 * 头部提示行提示“  某某同学你好， 请选择 签到 OR 签退  ”
//                                 *
//                                 */
//                                jButtonSignIn.setEnabled(true);
//
//                                JSONObject jsonObjectResult = JSONObject.fromObject(jsonjt.optJSONObject("result"));
//
//                                String face_token = jsonObjectResult.optString("face_token");
//
//                                JSONArray jsonArrayUserList = jsonObjectResult.optJSONArray("user_list");
//                                String group_id = jsonArrayUserList.optJSONObject(0).optString("group_id");
//                                String user_id = jsonArrayUserList.optJSONObject(0).optString("user_id");
//                                final String user_info = jsonArrayUserList.optJSONObject(0).optString("user_info");
//                                double score = jsonArrayUserList.optJSONObject(0).optDouble("score");
//
//                                if(score<80){
//                                    //若人脸搜索失败设置签到签退按钮不能使用
//                                    jButtonSignIn.setEnabled(true);
//                                    jButtonSignBack.setEnabled(true);
//                                    jTextFieldInfo.setText("无法捕捉正常人脸图像，请换个姿势重新尝试");
//                                    jTextFieldInfo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//                                    throw new Exception("该人脸信息在人脸库中不存在！");
//
//                                } else {
//                                    /*
//                                用来头部提示行使用
//                                 */
//                                    final String name = user_info;
//                                    final String id = user_id;
//
//                                    jTextFieldInfo.setText(name + "同学你好！" + "请选择 签到 OR 签退！");
//                                    jTextFieldInfo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//
//
//                                    /**
//                                     * 根据数据库考勤情况
//                                     * 设置签到 获签退按钮是否可用
//                                     * 待实现
//                                     *
//                                     * 通过数据库的签到签退标识来判断此时XX同学应该签到还是签退
//                                     */
//                                    try {
//                                        DB db = new DB();
//                                        ResultSet rs = null;
//                                        rs = db.executeQuery("select * from 考勤表 where 学号 = '" + id + "'");
//                                        //测试SQL语句正确性
//                                        System.out.println("select * from 考勤表 where 学号 = '" + id + "'");
//
//                                        if (rs.wasNull()) {
//                                            System.out.println("数据库不存在此用户请检查后台数据库数据");
//                                        } else {
//
//                                            /**
//                                             * 接下来判断签到签退符号位来控制按钮是否可使用
//                                             */
//                                            rs.next();
//                                            String database_id = rs.getString("学号");
//
//                                            int flag = rs.getInt("签到签退");
//
//                                            if (flag == 0) {
//                                                jButtonSignIn.setEnabled(true);
//                                                jButtonSignBack.setEnabled(false);
//                                            } else if (flag == 1) {
//                                                jButtonSignIn.setEnabled(false);
//                                                jButtonSignBack.setEnabled(true);
//                                            }
//                                        }
//                                    } catch (SQLException e1) {
//                                        e1.printStackTrace();
//                                    }
//
//
//                                    jButtonSignIn.addActionListener(new ActionListener() {
//                                        @Override
//                                        public void actionPerformed(ActionEvent e) {
//
//                                            System.out.println("测试： 用户按下了签到按钮");
//
//                                                /**
//                                                 * 接下来连接数据库
//                                                 * 增加修改考勤数据
//                                                 * 具体还未实现
//                                                 *
//                                                 * 数据库操作成功后
//                                                 * 头部提示框提示 xxx同学 签到成功
//                                                 */
//                                            DB db = new DB();
//                                            boolean result1 = db.executeUpdate("update 考勤表 set 签到签退 = 1 where 学号 = '" + id + "'" );
//                                            boolean result2 = db.executeUpdate("update 考勤表 set 最近一次签到签退时间 = now() where 学号 = '" + id + "'" );
//
//                                            if (result1 && result2) {
//                                                jTextFieldInfo.setText(name + "同学签到成功");
//                                            }
//
//
//
//                                        }
//                                    });
//
//                                    jButtonSignBack.addActionListener(new ActionListener() {
//                                        @Override
//                                        public void actionPerformed(ActionEvent e) {
//
//                                                System.out.println("测试： 用户按下了签退按钮");
//                                                /**
//                                                 * 接下来连接数据库
//                                                 * 增加修改考勤数据
//                                                 * 有待实现
//                                                 *
//                                                 * 数据库操作成功后
//                                                 *  头部提示框提示 xxx同学 签退成功
//                                                 */
//                                            DB db = new DB();
//                                            boolean result1 = db.executeUpdate("update 考勤表 set 签到签退 = 0 where 学生姓名 = '" + user_info + "'" );
//                                            boolean result2 = db.executeUpdate("update 考勤表 set 最近一次签到签退时间 = now() where 学生姓名 = '" + user_info + "'" );
//
//                                            if (result1 && result2) {
//                                                jTextFieldInfo.setText(name + "同学签退成功");
//                                            }
//
//
//                                        }
//                                    });
//
//                                }
//
//                                System.out.println("face_token: " + face_token);
//                                System.out.println("group_id: "+ group_id);
//                                System.out.println("user_info: "+ user_info);
//                                System.out.println("scores: "+ score);
//
//
//                            }else {
//
//                                System.out.println("人脸搜索失败");
//
//                            }
//                        } catch (Exception e) {
//
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//
//
//            }
//        });

        /**
         * 实现伪实时人脸检测
         */
        RealTimeFaceReco(this);


    }

    public void RealTimeFaceReco(final AttendanceCheckingPage attendanceCheckingPage) {
        // run in half a second
        final long timeInterval = 1000; //ms

        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    /**
                     * 核心代码
                     * 检测人脸
                     * 识别人脸并修改默认签到签退标志
                     */
                    String fileName = "D://Java大作业//FaceRecoDemo//out//artifacts//FaceRecoDemo_jar//LocalDataBase/" + System.currentTimeMillis();       //保存路径即图片名称（不用加后缀）
                    WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_JPG);



                    /**       图片转码       **/
                    String filename = fileName + ".jpg";
                    System.out.println("filename is " + filename);
                    byte[] imgData;
                    imgData = FileUtil.readFileByBytes(filename);
                    String imgStr = Base64Util.encode(imgData);
                    if (imgStr == null) {
                        throw new Exception("警告：人脸图像数据转换失败为空！");
                    }
                    String imgParam = null;
                    imgParam = URLEncoder.encode(imgStr, "UTF-8"); //base64 编码



                    /**   人脸检测  **/
                    FacebaiduDeal face = new FacebaiduDeal();
                    String error_code = face.detect(imgParam);
                    if (!error_code.equals("0")) {
                        jTextFieldInfo.setText("同学你好！" + "请将脸放置于检测范围（仅检测最大得一张人脸）");
                        jTextFieldInfo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                    } else {
                        /**   人脸搜索  **/
                        /**
                         * 核心人脸识别接口 facesearch()
                         */
                        JSONObject jsonjt = face.facesearch(imgParam);

                        /**
                         * 开始解析JSON
                         * 根据返回错误码来判断是否人脸搜索成功
                         * 但是是否有这个人 还需从score来判断
                         */
                        String error_msg = jsonjt.optString("error_msg");
                        String errorcode = jsonjt.optString("error_code");

                        System.out.println("errormsg:"+error_msg);

                        if(errorcode.equals("0")) {

                            /**
                             * 人脸搜索成功 即考勤成功 至于签到还是签退 按钮能否点击提示用户
                             * 头部提示行提示“  某某同学你好， 签到 OR 签退成功  ”
                             *
                             */
                            jButtonSignIn.setEnabled(true);

                            JSONObject jsonObjectResult = JSONObject.fromObject(jsonjt.optJSONObject("result"));

                            String face_token = jsonObjectResult.optString("face_token");

                            JSONArray jsonArrayUserList = jsonObjectResult.optJSONArray("user_list");
                            String group_id = jsonArrayUserList.optJSONObject(0).optString("group_id");
                            String user_id = jsonArrayUserList.optJSONObject(0).optString("user_id");
                            final String user_info = jsonArrayUserList.optJSONObject(0).optString("user_info");
                            double score = jsonArrayUserList.optJSONObject(0).optDouble("score");

                            if (score < 80) {
                                //若人脸搜索失败设置签到签退按钮不能使用
                                jButtonSignIn.setEnabled(true);
                                jButtonSignBack.setEnabled(true);
                                jTextFieldInfo.setText("无法捕捉正常人脸图像，请换个姿势重新尝试");
                                jTextFieldInfo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
                                throw new Exception("该人脸信息在人脸库中不存在！");

                            } else {
                                    /*
                                用来头部提示行使用
                                 */
                                final String name = user_info;
                                final String id = user_id;


                                /**
                                 * 根据数据库考勤时间记录
                                 * 是否可签退or签到
                                 * 待实现
                                 *
                                 * 通过数据库的签到签退标识来判断此时XX同学应该签到还是签退
                                 */
                                try {
                                    DB db = new DB();
                                    ResultSet rs = null;
                                    rs = db.executeQuery("select * from view_attendancechecking where s_id = '" + id + "'");
                                    //测试SQL语句正确性
                                    System.out.println("select * from view_attendancechecking where s_id = '" + id + "'");

                                    if (rs.wasNull()) {
                                        System.out.println("数据库不存在此用户请检查后台数据库数据");
                                    } else {

                                        /**
                                         * 接下来判断签到签退符号
                                         */
                                        rs.next();
                                        String database_id = rs.getString("s_id");

                                        int flag = rs.getInt("s_flag");
                                        Timestamp timestamp = rs.getTimestamp("s_lastcheck");
                                        Timestamp currenttime = new Timestamp(System.currentTimeMillis());

                                        if (flag == 0 && (currenttime.getTime() - timestamp.getTime()) > 1000*60 ) {
                                            //flag->1
                                            /**
                                             * 接下来连接数据库
                                             * 增加修改考勤数据
                                             *
                                             * 数据库操作成功后
                                             * 头部提示框提示 xxx同学 签到成功
                                             */
                                            DB db2 = new DB();
                                            boolean result1 = db2.executeUpdate("update view_attendancechecking set s_flag = 1 where s_id = '" + id + "'" );
                                            boolean result2 = db2.executeUpdate("update view_attendancechecking set s_lastcheck = now() where s_id = '" + id + "'" );

                                            if (result1 && result2) {
                                                jTextFieldInfo.setText(name + "同学签到成功");
                                            }
                                        } else if (flag == 1 && (currenttime.getTime() - timestamp.getTime()) > 1000*60 ) {
                                            //flag->0
                                            /**
                                             * 接下来连接数据库
                                             * 增加修改考勤数据
                                             *
                                             * 数据库操作成功后
                                             *  头部提示框提示 xxx同学 签退成功
                                             */
                                            DB db3 = new DB();
                                            boolean result1 = db3.executeUpdate("update view_attendancechecking set s_flag = 0 where s_name = '" + user_info + "'" );
                                            boolean result2 = db3.executeUpdate("update view_attendancechecking set s_lastcheck = now() where s_name = '" + user_info + "'" );

                                            if (result1 && result2) {
                                                jTextFieldInfo.setText(name + "同学签退成功");
                                            }

                                        }
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                attendanceCheckingPage.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);

                        timer.cancel();
                        webcamPanel.stop();
                        webcam.close();
                    }
                });
            }
        }, 0, 1000);




//        Runnable runnable = new Runnable() {
//
//            public void run() {
//
//                // ------- code for task to run
//                while (true) {
//
//
//                    // ------- ends here
//                    try {
//                        Thread.sleep(timeInterval);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//
//
//        };
//        final Thread thread = new Thread(runnable);
//        thread.start();
    }


    public static void main(String[] args) throws InterruptedException {

        AttendanceCheckingPage attendanceCheckingPage = new AttendanceCheckingPage();
    }
}
