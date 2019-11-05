package cn.cyan.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;


/**
 * @Author: Cyan
 * @Date: 2019/5/20 8:10
 * 考勤入口 和基本信息修改入口 和考勤数据查看 与 特殊情况 如请假信息添加
 * java swing 只关闭当前窗体 不关闭其他窗体
 * https://blog.csdn.net/u012721519/article/details/51068993
 *
 * imageicon转image
 * Image image=icon.getImage();
 * https://zhidao.baidu.com/question/54882399.html
 */
public class AdminPage extends JFrame{

//    private JFrame jFrameAdmin;
    private JPanel jPanelAdmin;
    //    private JPanel jPanelBasicInfo;
//    private JPanel jPanelAttendanceChecking;
//    private JPanel jPanelAttendanceData;
    private JLabel jLabelBasicInfo;
    private JLabel jLabelBasicInfoText;
    private JLabel jLabelDataManipulation;
    private JLabel jLabelDataManipulationText;
    private JLabel jLabelFaceUploading;
    private JLabel jLabelFaceUploadingText;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private String userAccount;
    private String userPassword;
    private static String userType = "管理员";


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public AdminPage() {
//        jFrameAdmin = new JFrame();
        /**总面板*/
        jPanelAdmin = new JPanel();

        /**基本信息图标标签**/
        jLabelBasicInfo = new JLabel();
        jLabelBasicInfoText = new JLabel("管理员信息");
        /**系统后台数据操作图标标签**/
        jLabelDataManipulation = new JLabel();
        jLabelDataManipulationText = new JLabel("系统数据管理");
        /**人脸录入图标标签**/
        jLabelFaceUploading = new JLabel();
        jLabelFaceUploadingText = new JLabel("人脸信息录入");

        /**网格包布局**/
        gridBagLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();

        //修改左上角Java图标
        URL imgURL = this.getClass().getResource("/viewingImg/titleicon.png");
        ImageIcon imageIcon = new ImageIcon(imgURL);
        java.awt.Image image = imageIcon.getImage();
        this.setIconImage(image);

        init();


    }

    public void init() {
        this.setTitle("考勤系统管理员界面");
//        jFrameTeacher.setLayout(null);
//        jFrameTeacher.setBounds(300, 200, 1200, 800);
//        jFrameTeacher.setLocationRelativeTo(null);
//        this.setAlwaysOnTop(true);
//        this.setUndecorated(false);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch(Exception e) {
            //TODO exception
            e.printStackTrace();
        }

        jPanelAdmin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                try {
                    /**
                     * image如何创建对象
                     * https://blog.csdn.net/u012723673/article/details/53326257
                     */
//                    BufferedImage bufferedImage = ImageIO.read(new File("viewingImg/AdminPageBackground.png"));
//                    ImageData imageData = new ImageData("AdminPageBackground.png");
//                    Display display = new Display();
//                    BufferedImage awtImage = convertToAWT(imageData);
//                    Image swtImage = new Image(display, convertToSWT(awtImage));
                    URL imgURL = this.getClass().getResource("/viewingImg/AdminPageBackground.png");
                    ImageIcon imageIcon = new ImageIcon(imgURL);
                    java.awt.Image image = imageIcon.getImage();
                    /**
                     * 获取屏幕的高度与宽度（两种方式）
                     * https://blog.csdn.net/jobfyz/article/details/78466171
                     */
                    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int width = dimension.width;  //1707px
                    int height = dimension.height;  //960px
                    g.drawImage(image, 0, 0, width, height, this);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        };

        //基本信息入口图标标签
        int iconwidth1 = 128;
        int iconheight1 = 128;

        URL imgURL1 = this.getClass().getResource("/viewingImg/basicinfo.png");
        ImageIcon imageIcon1 = new ImageIcon(imgURL1);
//        ImageIcon imageIcon1;
//        imageIcon1 = new ImageIcon("viewingImg/基本信息.png");
        java.awt.Image image1 = imageIcon1.getImage();
        image1 = image1.getScaledInstance(iconwidth1, iconheight1, 1);
        imageIcon1.setImage(image1);
        jLabelBasicInfo.setIcon(imageIcon1);
//        jPanelBasicInfo.add(jLabelBasicInfo);
//        jPanelBasicInfo.setBounds(70, 200, iconwidth1, iconheight1);


        //系统数据管理入口图标标签
        int iconwidth2 = 160;
        int iconheight2 = 160;
        URL imgURL2 = this.getClass().getResource("/viewingImg/data.png");
        ImageIcon imageIcon2 = new ImageIcon(imgURL2);
//        ImageIcon imageIcon2;
//        imageIcon2 = new ImageIcon("viewingImg/数据.png");
        java.awt.Image image2 = imageIcon1.getImage();
        image2 = image2.getScaledInstance(iconwidth1, iconheight1, 1);
        imageIcon1.setImage(image2);
        jLabelDataManipulation.setIcon(imageIcon2);
//        jPanelAttendanceChecking.add(jLabelAttendanceChecking);
//        jPanelAttendanceChecking.setBounds(400, 200, iconwidth2, iconheight2);

        //人脸录入入口图标标签
        int iconwidth3 = 128;
        int iconheight3 = 128;

        URL imgURL3 = this.getClass().getResource("/viewingImg/face.png");
        ImageIcon imageIcon3 = new ImageIcon(imgURL3);
//        ImageIcon imageIcon3;
//        imageIcon3 = new ImageIcon("viewingImg/人脸.png");
        java.awt.Image image3 = imageIcon1.getImage();
        image3 = image3.getScaledInstance(iconwidth1, iconheight1, 1);
        imageIcon1.setImage(image3);
        jLabelFaceUploading.setIcon(imageIcon3);
//        jPanelAttendanceData.add(jLabelAttendanceData);
//        jPanelAttendanceData.setBounds(800, 200, iconwidth3, iconheight3);


        /**
         * 文字标签
         */
        Color color = new Color(191, 173, 111);
        jLabelBasicInfoText.setFont(new Font("微软雅黑", Font.BOLD, 24));
        jLabelBasicInfoText.setForeground(color);
        jLabelDataManipulationText.setFont(new Font("微软雅黑", Font.BOLD, 24));
        jLabelDataManipulationText.setForeground(color);
        jLabelFaceUploadingText.setFont(new Font("微软雅黑", Font.BOLD, 24));
        jLabelFaceUploadingText.setForeground(color);



        /**
         * 给系统数据管理图标标签添加监听事件
         * 如何javaSwing关闭子窗口不关闭父窗口
         * http://www.cnblogs.com/hualidezhuanshen/p/5433572.html
         * 给系统数据管理图标标签添加鼠标监听事件
         * https://blog.csdn.net/xuqimm/article/details/71170300
         */
        jLabelDataManipulation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AdminDataManipulationPage adminDataManipulationPage = new AdminDataManipulationPage();
            }
            /**
             * 图标变换
             * 鼠标点击
             * 鼠标进入
             * 鼠标离开
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });


        jLabelDataManipulationText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AdminDataManipulationPage adminDataManipulationPage = new AdminDataManipulationPage();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });


        /**
         * 管理员基本信息图标标签和文字标签添加鼠标监听事件
         */
        jLabelBasicInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AdminBasicInfoPage adminBasicInfoPage = new AdminBasicInfoPage();
                String account = getUserAccount();
                String pwd = getUserPassword();

                adminBasicInfoPage.setUserAccount(account);
                adminBasicInfoPage.setUserPassword(pwd);

                adminBasicInfoPage.receTheAccountInfo(adminBasicInfoPage);

            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        jLabelBasicInfoText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                AdminBasicInfoPage adminBasicInfoPage = new AdminBasicInfoPage();
                String account = getUserAccount();
                String pwd = getUserPassword();

                adminBasicInfoPage.setUserAccount(account);
                adminBasicInfoPage.setUserPassword(pwd);

                adminBasicInfoPage.receTheAccountInfo(adminBasicInfoPage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        /**
         * 考勤后台数据图标标签和文字标签添加鼠标监听事件
         */
        jLabelFaceUploading.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                FaceUploadingPage faceUploadingPage = new FaceUploadingPage();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

        jLabelFaceUploadingText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                FaceUploadingPage faceUploadingPage = new FaceUploadingPage();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });


        /**
         * 设置JPanelTeacher
         * 为网格包布局
         */
        jPanelAdmin.setOpaque(false);
        jPanelAdmin.setLayout(gridBagLayout);

        /**
         * 接下来设置各组件以适应网格包布局
         */
        //基本信息入口标签显示
        gridBagConstraints = new GridBagConstraints(0, 0, 6, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 100, 0, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelBasicInfo, gridBagConstraints);
        jPanelAdmin.add(jLabelBasicInfo);

        //考勤入口标签显示
        gridBagConstraints = new GridBagConstraints(12, 0, 6, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 0, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelDataManipulation, gridBagConstraints);
        jPanelAdmin.add(jLabelDataManipulation);

        //考勤后台数据标签显示
        gridBagConstraints = new GridBagConstraints(24, 0, 6, 3, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 0, 100 ),0, 0);
        gridBagLayout.setConstraints(jLabelFaceUploading, gridBagConstraints);
        jPanelAdmin.add(jLabelFaceUploading);

        //基本信息文字内容标签内容
        gridBagConstraints = new GridBagConstraints(0, 6, 6, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 100, 120, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelBasicInfoText, gridBagConstraints);
        jPanelAdmin.add(jLabelBasicInfoText);

        //考勤入口文字内容标签内容
        gridBagConstraints = new GridBagConstraints(12, 6, 6, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 120, 120 ),0, 0);
        gridBagLayout.setConstraints(jLabelDataManipulationText, gridBagConstraints);
        jPanelAdmin.add(jLabelDataManipulationText);

        //考勤数据文字内容标签内容
        gridBagConstraints = new GridBagConstraints(24, 6, 6, 2, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 120, 120, 100 ),0, 0);
        gridBagLayout.setConstraints(jLabelFaceUploadingText, gridBagConstraints);
        jPanelAdmin.add(jLabelFaceUploadingText);


        this.add(jPanelAdmin);
    }




    public void showFrameAdmin() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private  static GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth,
                                                             int gridheight, double weightx, double weighty,
                                                             int anchor, int fill, Insets insets, int ipadx,
                                                             int ipady) {
        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor,
                fill, insets, ipadx, ipady);
    }

    /**
     *
     * @param data
     * @return
     */
    public static BufferedImage convertToAWT(ImageData data) {
        ColorModel colorModel = null;
        PaletteData palette = data.palette;
        if (palette.isDirect) {
            colorModel = new DirectColorModel(data.depth, palette.redMask,
                    palette.greenMask, palette.blueMask);
            BufferedImage bufferedImage = new BufferedImage(colorModel,
                    colorModel.createCompatibleWritableRaster(data.width,
                            data.height), false, null);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[3];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    RGB rgb = palette.getRGB(pixel);
                    pixelArray[0] = rgb.red;
                    pixelArray[1] = rgb.green;
                    pixelArray[2] = rgb.blue;
                    raster.setPixels(x, y, 1, 1, pixelArray);
                }
            }
            return bufferedImage;
        } else {
            RGB[] rgbs = palette.getRGBs();
            byte[] red = new byte[rgbs.length];
            byte[] green = new byte[rgbs.length];
            byte[] blue = new byte[rgbs.length];
            for (int i = 0; i < rgbs.length; i++) {
                RGB rgb = rgbs[i];
                red[i] = (byte) rgb.red;
                green[i] = (byte) rgb.green;
                blue[i] = (byte) rgb.blue;
            }
            if (data.transparentPixel != -1) {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                        green, blue, data.transparentPixel);
            } else {
                colorModel = new IndexColorModel(data.depth, rgbs.length, red,
                        green, blue);
            }
            BufferedImage bufferedImage = new BufferedImage(colorModel,
                    colorModel.createCompatibleWritableRaster(data.width,
                            data.height), false, null);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    int pixel = data.getPixel(x, y);
                    pixelArray[0] = pixel;
                    raster.setPixel(x, y, pixelArray);
                }
            }
            return bufferedImage;
        }
    }

    /**
     *
     * @param bufferedImage
     * @return
     */
    static ImageData convertToSWT(BufferedImage bufferedImage) {
        if (bufferedImage.getColorModel() instanceof DirectColorModel) {
            DirectColorModel colorModel = (DirectColorModel) bufferedImage
                    .getColorModel();
            PaletteData palette = new PaletteData(colorModel.getRedMask(),
                    colorModel.getGreenMask(), colorModel.getBlueMask());
            ImageData data = new ImageData(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), colorModel.getPixelSize(),
                    palette);
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[3];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    int pixel = palette.getPixel(new RGB(pixelArray[0],
                            pixelArray[1], pixelArray[2]));
                    data.setPixel(x, y, pixel);
                }
            }
            return data;
        } else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
            IndexColorModel colorModel = (IndexColorModel) bufferedImage
                    .getColorModel();
            int size = colorModel.getMapSize();
            byte[] reds = new byte[size];
            byte[] greens = new byte[size];
            byte[] blues = new byte[size];
            colorModel.getReds(reds);
            colorModel.getGreens(greens);
            colorModel.getBlues(blues);
            RGB[] rgbs = new RGB[size];
            for (int i = 0; i < rgbs.length; i++) {
                rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF,
                        blues[i] & 0xFF);
            }
            PaletteData palette = new PaletteData(rgbs);
            ImageData data = new ImageData(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), colorModel.getPixelSize(),
                    palette);
            data.transparentPixel = colorModel.getTransparentPixel();
            WritableRaster raster = bufferedImage.getRaster();
            int[] pixelArray = new int[1];
            for (int y = 0; y < data.height; y++) {
                for (int x = 0; x < data.width; x++) {
                    raster.getPixel(x, y, pixelArray);
                    data.setPixel(x, y, pixelArray[0]);
                }
            }
            return data;
        }
        return null;
    }

    static ImageData createSampleImage(Display display) {
        Image image = new Image(display, 100, 100);
        Rectangle bounds = image.getBounds();
        GC gc = new GC(image);
        gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
        gc.fillRectangle(bounds);
        gc.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
        gc.fillOval(0, 0, bounds.width, bounds.height);
        gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
        gc.drawLine(0, 0, bounds.width, bounds.height);
        gc.drawLine(bounds.width, 0, 0, bounds.height);
        gc.dispose();
        ImageData data = image.getImageData();
        image.dispose();
        return data;
    }

    public static void main(String[] args) {
        AdminPage adminPage = new AdminPage();
        adminPage.showFrameAdmin();
    }
}
