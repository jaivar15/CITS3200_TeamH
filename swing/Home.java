/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.util.ArrayList;
/**
 *
 * @author y2434
 */
public class Home extends javax.swing.JFrame {
    private JComboBox choiceBox;
    private DefaultTableModel model ;
    private ArrayList<?>[][] animalInfo;
    private int size;
    private responder user;
    private ArrayList<String> AnimalName;
    /**
     * Creates new form Home
     */
    public Home() {
        AnimalName = new ArrayList<String>();
        AnimalName.add("cat");
        AnimalName.add("cat1");
        AnimalName.add("cat2");
        AnimalName.add("cat3");
        user = new responder();
        animalInfo = new ArrayList<?>[1][10];
        frame = new JFrame("Display Mode");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        initComponents();
        setUpModel();
        setColor(btn_1); 
        resetColor(new JPanel[]{btn_4,btn_3,btn_5,btn_2});
    }
    
    public Home(Object[] o){
        user = new responder((Object[])o[1]);
        animalInfo = new ArrayList<?>[1][10];
        frame = new JFrame("Display Mode");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        initComponents();
        setUpModel();
        setColor(btn_1); 
        resetColor(new JPanel[]{btn_4,btn_3,btn_5,btn_2});
    }

    
    private void setUpModel(){        
        Object oo [][] = user.UserInfoOutPut();
        
        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            oo,
            new String [] {
                "Name", "Email", "Animal Responsed",
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        HomeTable.setModel(new javax.swing.table.DefaultTableModel(
            animalInfo,
            new String [] {
                "Animal ID", "Animal Name", "condiction",
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        addAnimalTable.setModel(new javax.swing.table.DefaultTableModel(
            animalInfo,
            new String [] {
                "Animal Name", "Animal Description", "S.D.", "Days", "Duration",
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        changeIdTable.setModel(new javax.swing.table.DefaultTableModel(
            animalInfo,
            new String [] {
                "Animal Name", "Animal Description", "S.D.", "Days", "Duration", "Click"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btn_1 = new javax.swing.JPanel();
        HomeTextLabel = new javax.swing.JLabel();
        HomeIconLabel = new javax.swing.JLabel();
        btn_3 = new javax.swing.JPanel();
        AddAnimalTextLabel = new javax.swing.JLabel();
        AnimaliconLabel = new javax.swing.JLabel();
        btn_5 = new javax.swing.JPanel();
        SettingTextLabel = new javax.swing.JLabel();
        SettingIconLabel = new javax.swing.JLabel();
        btn_2 = new javax.swing.JPanel();
        UserTextLabel = new javax.swing.JLabel();
        EmailiconLabel = new javax.swing.JLabel();
        btn_4 = new javax.swing.JPanel();
        changeIdTextLabel = new javax.swing.JLabel();
        ChangeIconLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        MainIconLabel = new javax.swing.JLabel();
        searchVarText = new javax.swing.JTextField();
        SearchIconLabel = new javax.swing.JLabel();
        UWATextLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        HomePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HomeTable = new javax.swing.JTable();
        usersPanel = new javax.swing.JPanel();
        EmailAccountText = new javax.swing.JTextField();
        EmailNameText = new javax.swing.JTextField();
        EmailNameTextLable = new javax.swing.JLabel();
        EmailAccountTextLabel = new javax.swing.JLabel();
        AddIntoButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        AnimalList = new javax.swing.JList<>();
        addAnimalPanel = new javax.swing.JPanel();
        AnimalNameText = new javax.swing.JTextField();
        StandDeviationText = new javax.swing.JTextField();
        AnimalDescriptionText = new javax.swing.JTextField();
        NumberOfDaysText = new javax.swing.JTextField();
        DurationText = new javax.swing.JTextField();
        StandardDeviationLabel = new javax.swing.JLabel();
        AnimalNameLabel = new javax.swing.JLabel();
        AnimalDescriptionLabel = new javax.swing.JLabel();
        NumberOfDaysLable = new javax.swing.JLabel();
        DurationLabel = new javax.swing.JLabel();
        ChoseFileButton = new javax.swing.JButton();
        lblFile = new javax.swing.JLabel();
        Submit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        addAnimalTable = new javax.swing.JTable();
        changeIDPanel = new javax.swing.JPanel();
        ChangeStandardDeviationLabel = new javax.swing.JLabel();
        ChangeDurationLabel = new javax.swing.JLabel();
        ChangeStandDeviationText = new javax.swing.JTextField();
        ChangeAnimalDescriptionText = new javax.swing.JTextField();
        ChangeAnimalNameText = new javax.swing.JTextField();
        ChangeNumberOfDaysText = new javax.swing.JTextField();
        ChangeAnimalNameLabel = new javax.swing.JLabel();
        ChangeDurationText = new javax.swing.JTextField();
        ChangeAnimalDescriptionLabel = new javax.swing.JLabel();
        ChangeNumberOfDaysLable = new javax.swing.JLabel();
        ChangeIDButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        changeIdTable = new javax.swing.JTable();
        settingPanel = new javax.swing.JPanel();
        ApplicationPasswordButton = new javax.swing.JButton();
        OwnerEmailButton = new javax.swing.JButton();
        AdvanceFeatureButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 102, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(23, 35, 51));

        btn_1.setBackground(new java.awt.Color(23, 35, 51));
        btn_1.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1MousePressed(evt);
            }
        });

        HomeTextLabel.setFont(new java.awt.Font("Segoe Script", 2, 18)); // NOI18N
        HomeTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        HomeTextLabel.setText("Home");

        HomeIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/Home.png"))); // NOI18N

        javax.swing.GroupLayout btn_1Layout = new javax.swing.GroupLayout(btn_1);
        btn_1.setLayout(btn_1Layout);
        btn_1Layout.setHorizontalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(HomeIconLabel)
                .addGap(18, 18, 18)
                .addComponent(HomeTextLabel)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        btn_1Layout.setVerticalGroup(
            btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(btn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HomeIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(HomeTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btn_3.setBackground(new java.awt.Color(23, 35, 51));
        btn_3.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_3MousePressed(evt);
            }
        });

        AddAnimalTextLabel.setFont(new java.awt.Font("Segoe Script", 2, 14)); // NOI18N
        AddAnimalTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        AddAnimalTextLabel.setText("Add Animals");

        AnimaliconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/Animal.png"))); // NOI18N

        javax.swing.GroupLayout btn_3Layout = new javax.swing.GroupLayout(btn_3);
        btn_3.setLayout(btn_3Layout);
        btn_3Layout.setHorizontalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_3Layout.createSequentialGroup()
                .addComponent(AnimaliconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(AddAnimalTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btn_3Layout.setVerticalGroup(
            btn_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AnimaliconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AddAnimalTextLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_5.setBackground(new java.awt.Color(23, 35, 51));
        btn_5.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_5MousePressed(evt);
            }
        });

        SettingTextLabel.setFont(new java.awt.Font("Segoe Script", 2, 18)); // NOI18N
        SettingTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        SettingTextLabel.setText("Setting");

        SettingIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/Settings.png"))); // NOI18N

        javax.swing.GroupLayout btn_5Layout = new javax.swing.GroupLayout(btn_5);
        btn_5.setLayout(btn_5Layout);
        btn_5Layout.setHorizontalGroup(
            btn_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_5Layout.createSequentialGroup()
                .addGap(0, 42, Short.MAX_VALUE)
                .addComponent(SettingIconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SettingTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        btn_5Layout.setVerticalGroup(
            btn_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SettingIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SettingTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_2.setBackground(new java.awt.Color(23, 35, 51));
        btn_2.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_2MousePressed(evt);
            }
        });

        UserTextLabel.setFont(new java.awt.Font("Segoe Script", 2, 18)); // NOI18N
        UserTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        UserTextLabel.setText("Users");

        EmailiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/Send_Email.png"))); // NOI18N

        javax.swing.GroupLayout btn_2Layout = new javax.swing.GroupLayout(btn_2);
        btn_2.setLayout(btn_2Layout);
        btn_2Layout.setHorizontalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(EmailiconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UserTextLabel)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        btn_2Layout.setVerticalGroup(
            btn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(EmailiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btn_4.setBackground(new java.awt.Color(23, 35, 51));
        btn_4.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_4MousePressed(evt);
            }
        });

        changeIdTextLabel.setFont(new java.awt.Font("Segoe Script", 2, 18)); // NOI18N
        changeIdTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        changeIdTextLabel.setText("Change ID");

        ChangeIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/Change.png"))); // NOI18N

        javax.swing.GroupLayout btn_4Layout = new javax.swing.GroupLayout(btn_4);
        btn_4.setLayout(btn_4Layout);
        btn_4Layout.setHorizontalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_4Layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(ChangeIconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeIdTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btn_4Layout.setVerticalGroup(
            btn_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ChangeIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(changeIdTextLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btn_1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btn_2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btn_4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btn_5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
            .addComponent(btn_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
            .addComponent(btn_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
            .addComponent(btn_4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
            .addComponent(btn_5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 950, 80));

        jPanel1.setBackground(new java.awt.Color(41, 83, 129));

        MainIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/Penguin_48px.png"))); // NOI18N
        MainIconLabel.setLabelFor(MainIconLabel);
        MainIconLabel.setText("jLabel1");

        searchVarText.setBackground(new java.awt.Color(42, 75, 115));
        searchVarText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        searchVarText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchVarTextActionPerformed(evt);
            }
        });

        SearchIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        SearchIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/image/searchs.png"))); // NOI18N
        SearchIconLabel.setToolTipText("");

        UWATextLabel.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        UWATextLabel.setForeground(new java.awt.Color(255, 255, 255));
        UWATextLabel.setText("The University Of Western Australia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(MainIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(searchVarText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchIconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(UWATextLabel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(MainIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchVarText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(UWATextLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(SearchIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        MainIconLabel.getAccessibleContext().setAccessibleName("ICON");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 50));

        jPanel3.setBackground(new java.awt.Color(242, 242, 242));
        jPanel3.setLayout(new java.awt.CardLayout());

        HomePanel.setBackground(new java.awt.Color(153, 255, 255));

        jScrollPane3.setViewportView(HomeTable);

        javax.swing.GroupLayout HomePanelLayout = new javax.swing.GroupLayout(HomePanel);
        HomePanel.setLayout(HomePanelLayout);
        HomePanelLayout.setHorizontalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomePanelLayout.createSequentialGroup()
                .addContainerGap(444, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HomePanelLayout.setVerticalGroup(
            HomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(HomePanel, "card2");
        HomePanel.getAccessibleContext().setAccessibleName("");

        usersPanel.setBackground(new java.awt.Color(153, 204, 255));

        EmailAccountText.setBackground(new java.awt.Color(42, 75, 115));
        EmailAccountText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        EmailAccountText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailAccountTextActionPerformed(evt);
            }
        });

        EmailNameText.setBackground(new java.awt.Color(42, 75, 115));
        EmailNameText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));

        EmailNameTextLable.setText("Email Name");

        EmailAccountTextLabel.setText("Emails Account");

        AddIntoButton.setText("Add Into ");
        AddIntoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddIntoButtonActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(UserTable);

        AnimalList.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return AnimalName.size(); }
            public String getElementAt(int i) { return AnimalName.get(i); }
        });
        jScrollPane5.setViewportView(AnimalList);

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmailAccountTextLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addComponent(EmailNameTextLable)
                        .addGap(23, 23, 23)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmailNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmailAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(AddIntoButton)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmailNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmailNameTextLable))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmailAccountText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EmailAccountTextLabel))
                        .addGap(18, 18, 18)
                        .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddIntoButton)))
                    .addGroup(usersPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jPanel3.add(usersPanel, "card3");

        addAnimalPanel.setBackground(new java.awt.Color(153, 153, 255));

        AnimalNameText.setBackground(new java.awt.Color(42, 75, 115));
        AnimalNameText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        AnimalNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnimalNameTextActionPerformed(evt);
            }
        });

        StandDeviationText.setBackground(new java.awt.Color(42, 75, 115));
        StandDeviationText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        StandDeviationText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StandDeviationTextActionPerformed(evt);
            }
        });

        AnimalDescriptionText.setBackground(new java.awt.Color(42, 75, 115));
        AnimalDescriptionText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        AnimalDescriptionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnimalDescriptionTextActionPerformed(evt);
            }
        });

        NumberOfDaysText.setBackground(new java.awt.Color(42, 75, 115));
        NumberOfDaysText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        NumberOfDaysText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumberOfDaysTextActionPerformed(evt);
            }
        });

        DurationText.setBackground(new java.awt.Color(42, 75, 115));
        DurationText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        DurationText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DurationTextActionPerformed(evt);
            }
        });

        StandardDeviationLabel.setText("standard deviation");

        AnimalNameLabel.setText("Animal Name ");

        AnimalDescriptionLabel.setText("Animal Description ");

        NumberOfDaysLable.setText("Number of days");

        DurationLabel.setText("Duration");

        ChoseFileButton.setText("Chose the data file");
        ChoseFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChoseFileButtonActionPerformed(evt);
            }
        });

        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(addAnimalTable);

        javax.swing.GroupLayout addAnimalPanelLayout = new javax.swing.GroupLayout(addAnimalPanel);
        addAnimalPanel.setLayout(addAnimalPanelLayout);
        addAnimalPanelLayout.setHorizontalGroup(
            addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAnimalPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAnimalPanelLayout.createSequentialGroup()
                        .addGroup(addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addAnimalPanelLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(Submit))
                            .addGroup(addAnimalPanelLayout.createSequentialGroup()
                                .addGroup(addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AnimalNameLabel)
                                    .addComponent(StandardDeviationLabel)
                                    .addComponent(AnimalDescriptionLabel)
                                    .addComponent(NumberOfDaysLable)
                                    .addComponent(DurationLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(StandDeviationText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AnimalNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NumberOfDaysText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DurationText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AnimalDescriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(addAnimalPanelLayout.createSequentialGroup()
                        .addComponent(ChoseFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFile, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))))
        );
        addAnimalPanelLayout.setVerticalGroup(
            addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addAnimalPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addAnimalPanelLayout.createSequentialGroup()
                        .addComponent(AnimalNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(AnimalDescriptionLabel)
                        .addGap(12, 12, 12)
                        .addComponent(StandardDeviationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NumberOfDaysLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DurationLabel))
                    .addGroup(addAnimalPanelLayout.createSequentialGroup()
                        .addComponent(AnimalNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AnimalDescriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StandDeviationText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NumberOfDaysText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DurationText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(addAnimalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChoseFileButton)
                    .addComponent(lblFile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(Submit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addAnimalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(addAnimalPanel, "card4");

        changeIDPanel.setBackground(new java.awt.Color(204, 153, 255));

        ChangeStandardDeviationLabel.setText("standard deviation");

        ChangeDurationLabel.setText("Duration");

        ChangeStandDeviationText.setBackground(new java.awt.Color(42, 75, 115));
        ChangeStandDeviationText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        ChangeStandDeviationText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeStandDeviationTextActionPerformed(evt);
            }
        });

        ChangeAnimalDescriptionText.setBackground(new java.awt.Color(42, 75, 115));
        ChangeAnimalDescriptionText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        ChangeAnimalDescriptionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeAnimalDescriptionTextActionPerformed(evt);
            }
        });

        ChangeAnimalNameText.setBackground(new java.awt.Color(42, 75, 115));
        ChangeAnimalNameText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        ChangeAnimalNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeAnimalNameTextActionPerformed(evt);
            }
        });

        ChangeNumberOfDaysText.setBackground(new java.awt.Color(42, 75, 115));
        ChangeNumberOfDaysText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        ChangeNumberOfDaysText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeNumberOfDaysTextActionPerformed(evt);
            }
        });

        ChangeAnimalNameLabel.setText("Animal Name ");

        ChangeDurationText.setBackground(new java.awt.Color(42, 75, 115));
        ChangeDurationText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        ChangeDurationText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeDurationTextActionPerformed(evt);
            }
        });

        ChangeAnimalDescriptionLabel.setText("Animal Description ");

        ChangeNumberOfDaysLable.setText("Number of days");

        ChangeIDButton.setText("Change");
        ChangeIDButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ChangeIDButtonMousePressed(evt);
            }
        });
        ChangeIDButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeIDButtonActionPerformed(evt);
            }
        });

        changeIdTable.setAutoCreateRowSorter(true);
        changeIdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        changeIdTable.setColumnSelectionAllowed(true);
        changeIdTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(changeIdTable);
        changeIdTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout changeIDPanelLayout = new javax.swing.GroupLayout(changeIDPanel);
        changeIDPanel.setLayout(changeIDPanelLayout);
        changeIDPanelLayout.setHorizontalGroup(
            changeIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeIDPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(changeIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChangeAnimalNameLabel)
                    .addComponent(ChangeStandardDeviationLabel)
                    .addComponent(ChangeAnimalDescriptionLabel)
                    .addComponent(ChangeNumberOfDaysLable)
                    .addComponent(ChangeDurationLabel))
                .addGap(18, 18, 18)
                .addGroup(changeIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChangeStandDeviationText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeAnimalNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeNumberOfDaysText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeDurationText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeAnimalDescriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChangeIDButton)
                .addContainerGap(431, Short.MAX_VALUE))
            .addGroup(changeIDPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        changeIDPanelLayout.setVerticalGroup(
            changeIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeIDPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(changeIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeIDPanelLayout.createSequentialGroup()
                        .addComponent(ChangeAnimalNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(ChangeAnimalDescriptionLabel)
                        .addGap(12, 12, 12)
                        .addComponent(ChangeStandardDeviationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChangeNumberOfDaysLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ChangeDurationLabel))
                    .addGroup(changeIDPanelLayout.createSequentialGroup()
                        .addComponent(ChangeAnimalNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChangeAnimalDescriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChangeStandDeviationText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChangeNumberOfDaysText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(changeIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ChangeDurationText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ChangeIDButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        jPanel3.add(changeIDPanel, "card5");

        settingPanel.setBackground(new java.awt.Color(255, 204, 102));

        ApplicationPasswordButton.setText("Application password");
        ApplicationPasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ApplicationPasswordButtonMousePressed(evt);
            }
        });
        ApplicationPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplicationPasswordButtonActionPerformed(evt);
            }
        });

        OwnerEmailButton.setText("Owner Email");
        OwnerEmailButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OwnerEmailButtonMousePressed(evt);
            }
        });

        AdvanceFeatureButton.setText("Advance feature");
        AdvanceFeatureButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AdvanceFeatureButtonMousePressed(evt);
            }
        });
        AdvanceFeatureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdvanceFeatureButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingPanelLayout = new javax.swing.GroupLayout(settingPanel);
        settingPanel.setLayout(settingPanelLayout);
        settingPanelLayout.setHorizontalGroup(
            settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AdvanceFeatureButton)
                    .addComponent(OwnerEmailButton)
                    .addComponent(ApplicationPasswordButton))
                .addContainerGap(621, Short.MAX_VALUE))
        );
        settingPanelLayout.setVerticalGroup(
            settingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingPanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(ApplicationPasswordButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OwnerEmailButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdvanceFeatureButton)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        jPanel3.add(settingPanel, "card6");

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 950, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchVarTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchVarTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchVarTextActionPerformed

    private void btn_1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_1MousePressed
        // TODO add your handling code here:
        setColor(btn_1);
        resetColor( new JPanel[]{ btn_4, btn_3,  btn_5,btn_2});
        CardLayout card = (CardLayout)jPanel3.getLayout();
        card.show(jPanel3, "card2");
    }//GEN-LAST:event_btn_1MousePressed

    private void btn_4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_4MousePressed
        // TODO add your handling code here:
        setColor(btn_4);
        resetColor(new JPanel[]{ btn_1, btn_3,  btn_5,btn_2});
        CardLayout card = (CardLayout)jPanel3.getLayout();
        card.show(jPanel3, "card5");
        changeIdTable.updateUI();
    }//GEN-LAST:event_btn_4MousePressed

    private void btn_3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_3MousePressed
        // TODO add your handling code here:
        setColor(btn_3);
        resetColor( new JPanel[]{ btn_1, btn_4,  btn_5,btn_2});
        //addNew();
        CardLayout card = (CardLayout)jPanel3.getLayout();
        card.show(jPanel3, "card4");
    }//GEN-LAST:event_btn_3MousePressed

    private void btn_5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_5MousePressed
        // TODO add your handling code here:
        setColor(btn_5);
        resetColor( new JPanel[]{ btn_4, btn_3,btn_1,btn_2});
        CardLayout card = (CardLayout)jPanel3.getLayout();
        card.show(jPanel3, "card6");
    }//GEN-LAST:event_btn_5MousePressed

    private void btn_2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_2MousePressed
        // TODO add your handling code here:
        setColor(btn_2);
        resetColor( new JPanel[]{ btn_1, btn_4,btn_3,btn_5});
        CardLayout card = (CardLayout)jPanel3.getLayout();
        card.show(jPanel3, "card3");
    }//GEN-LAST:event_btn_2MousePressed

    private void AnimalNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnimalNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnimalNameTextActionPerformed

    private void StandDeviationTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StandDeviationTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StandDeviationTextActionPerformed

    private void AnimalDescriptionTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnimalDescriptionTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnimalDescriptionTextActionPerformed

    private void NumberOfDaysTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumberOfDaysTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumberOfDaysTextActionPerformed

    private void DurationTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DurationTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DurationTextActionPerformed

    private void EmailAccountTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailAccountTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailAccountTextActionPerformed

    private void ChoseFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoseFileButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
                j.setFileFilter(new FileNameExtensionFilter("Csv files", "csv"));

                // fileChosen tells you when a file has been chosen
                int fileChosen = j.showOpenDialog(null);
                if (fileChosen == 0); {
                    try {
                        String fileName = j.getSelectedFile().getAbsolutePath();
                        lblFile.setText(fileName);
                        fileName(fileName);
                    } catch (NullPointerException ex) {
                        
                    }
                }
    }//GEN-LAST:event_ChoseFileButtonActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
        actionPerformed();
    }//GEN-LAST:event_SubmitActionPerformed

    private void AddIntoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddIntoButtonActionPerformed
        // TODO add your handling code here:
        String name = EmailNameText.getText();
        String account = EmailAccountText.getText();
        if(!name.equals("") && !account.equals("")){
            boolean check;
            if((check = user.newUser(name, account)) == true){
                DefaultTableModel UserTableModel = (DefaultTableModel) UserTable.getModel();
                Object[] o = {name,account};
                UserTableModel.addRow(o);
            }else{
                JOptionPane.showMessageDialog(null, "user exist in system");
            }
        }else JOptionPane.showMessageDialog(null, "Please fill in all fields");
       UserTable.repaint();
    }//GEN-LAST:event_AddIntoButtonActionPerformed

    private void ChangeStandDeviationTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeStandDeviationTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangeStandDeviationTextActionPerformed

    private void ChangeAnimalDescriptionTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeAnimalDescriptionTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangeAnimalDescriptionTextActionPerformed

    private void ChangeAnimalNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeAnimalNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangeAnimalNameTextActionPerformed

    private void ChangeNumberOfDaysTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeNumberOfDaysTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangeNumberOfDaysTextActionPerformed

    private void ChangeDurationTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeDurationTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangeDurationTextActionPerformed

    private void ChangeIDButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeIDButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangeIDButtonActionPerformed

    private void ChangeIDButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChangeIDButtonMousePressed
        // TODO add your handling code here:
        checkUserPanelInput();
        DefaultTableModel model = (DefaultTableModel) changeIdTable.getModel();
        Object[] o = {ChangeAnimalNameText.getText(),ChangeAnimalDescriptionText.getText(),Double.valueOf(ChangeStandDeviationText.getText()),
        Integer.valueOf(ChangeNumberOfDaysText.getText()),Double.valueOf(ChangeDurationText.getText()),false};
        model.addRow(o);
        changeIdTable.repaint();
    }//GEN-LAST:event_ChangeIDButtonMousePressed

    private void ApplicationPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplicationPasswordButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApplicationPasswordButtonActionPerformed

    private void ApplicationPasswordButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApplicationPasswordButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApplicationPasswordButtonMousePressed

    private void OwnerEmailButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OwnerEmailButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OwnerEmailButtonMousePressed

    private void AdvanceFeatureButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdvanceFeatureButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdvanceFeatureButtonMousePressed

    private void AdvanceFeatureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdvanceFeatureButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdvanceFeatureButtonActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    new Home().setVisible(true);
                }catch (Exception e) {
			e.printStackTrace();
		}
            }
        });
    }
    private void fileName(String fileName){
        //String fileName = "";
        lblFile = new JLabel(fileName);
        lblFile.setBounds(394, 99, 92, 26);
        addAnimalPanel.add(lblFile);
    }
    
    public void actionPerformed() {
            if (checkInput()) {
                try {
                    String animalName = AnimalNameText.getText();
                    String animalDescription = AnimalDescriptionText.getText();
                    double deviation = Double.valueOf(StandDeviationText.getText());
                    int days = Integer.valueOf(NumberOfDaysText.getText());
                    double time = Double.valueOf(DurationText.getText());
                    String filePath = lblFile.getText();

                    //TODO
                    Object[] data = {animalName, animalDescription, deviation, days, time, filePath};
                    serialization ser = new serialization(data, "/Users/toomato/Desktop","test");
                    ser.SerializeObject();

                    JOptionPane.showMessageDialog(null, "Added successfully");
                    //close();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please check number fields only contain numbers");
                }
            }
        }
    
    private boolean checkInput() {
            AtomicBoolean returnSt = new AtomicBoolean(true);
            if (AnimalNameText.getText().equals("")) {
                returnSt.set(false);
            }else if(StandDeviationText.getText().equals("")){
                returnSt.set(false);
            }else if (NumberOfDaysText.getText().equals("")) {
                returnSt.set(false);
            } else if (DurationText.getText().equals("")) {
                returnSt.set(false);
            }
            
            if(!returnSt.get()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields and select a file 1");
            }
            return returnSt.get();
        }
    
    private boolean checkUserPanelInput() {
            AtomicBoolean returnSt = new AtomicBoolean(true);
            if (ChangeAnimalNameText.getText().equals("")) {
                returnSt.set(false);
            } else if (ChangeAnimalDescriptionText.getText().equals("") || ChangeStandDeviationText.getText().equals("")) returnSt.set(false);
            else if (ChangeNumberOfDaysText.getText().equals("")) {
                returnSt.set(false);
            } else if (ChangeDurationText.getText().equals("")) {
                returnSt.set(false);
            }
            if(!returnSt.get()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields and select a file 2");
            }
            return returnSt.get();
        }

    
    private void setColor(JPanel pane)
    {
        pane.setBackground(new Color(41,57,80));
    }
    
    private void resetColor(JPanel [] pane)
    {
        for(int i=0; i<pane.length; i++){
           pane[i].setBackground(new Color(23,35,51));
        }
    }
    
    private final JFrame frame;
    
    private void makeVisible() {
		this.setVisible(true);
	}
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddAnimalTextLabel;
    private javax.swing.JButton AddIntoButton;
    private javax.swing.JButton AdvanceFeatureButton;
    private javax.swing.JLabel AnimalDescriptionLabel;
    private javax.swing.JTextField AnimalDescriptionText;
    private javax.swing.JList<String> AnimalList;
    private javax.swing.JLabel AnimalNameLabel;
    private javax.swing.JTextField AnimalNameText;
    private javax.swing.JLabel AnimaliconLabel;
    private javax.swing.JButton ApplicationPasswordButton;
    private javax.swing.JLabel ChangeAnimalDescriptionLabel;
    private javax.swing.JTextField ChangeAnimalDescriptionText;
    private javax.swing.JLabel ChangeAnimalNameLabel;
    private javax.swing.JTextField ChangeAnimalNameText;
    private javax.swing.JLabel ChangeDurationLabel;
    private javax.swing.JTextField ChangeDurationText;
    private javax.swing.JButton ChangeIDButton;
    private javax.swing.JLabel ChangeIconLabel;
    private javax.swing.JLabel ChangeNumberOfDaysLable;
    private javax.swing.JTextField ChangeNumberOfDaysText;
    private javax.swing.JTextField ChangeStandDeviationText;
    private javax.swing.JLabel ChangeStandardDeviationLabel;
    private javax.swing.JButton ChoseFileButton;
    private javax.swing.JLabel DurationLabel;
    private javax.swing.JTextField DurationText;
    private javax.swing.JTextField EmailAccountText;
    private javax.swing.JLabel EmailAccountTextLabel;
    private javax.swing.JTextField EmailNameText;
    private javax.swing.JLabel EmailNameTextLable;
    private javax.swing.JLabel EmailiconLabel;
    private javax.swing.JLabel HomeIconLabel;
    private javax.swing.JPanel HomePanel;
    private javax.swing.JTable HomeTable;
    private javax.swing.JLabel HomeTextLabel;
    private javax.swing.JLabel MainIconLabel;
    private javax.swing.JLabel NumberOfDaysLable;
    private javax.swing.JTextField NumberOfDaysText;
    private javax.swing.JButton OwnerEmailButton;
    private javax.swing.JLabel SearchIconLabel;
    private javax.swing.JLabel SettingIconLabel;
    private javax.swing.JLabel SettingTextLabel;
    private javax.swing.JTextField StandDeviationText;
    private javax.swing.JLabel StandardDeviationLabel;
    private javax.swing.JButton Submit;
    private javax.swing.JLabel UWATextLabel;
    private javax.swing.JTable UserTable;
    private javax.swing.JLabel UserTextLabel;
    private javax.swing.JPanel addAnimalPanel;
    private javax.swing.JTable addAnimalTable;
    private javax.swing.JPanel btn_1;
    private javax.swing.JPanel btn_2;
    private javax.swing.JPanel btn_3;
    private javax.swing.JPanel btn_4;
    private javax.swing.JPanel btn_5;
    private javax.swing.JPanel changeIDPanel;
    private javax.swing.JTable changeIdTable;
    private javax.swing.JLabel changeIdTextLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblFile;
    private javax.swing.JTextField searchVarText;
    private javax.swing.JPanel settingPanel;
    private javax.swing.JPanel usersPanel;
    // End of variables declaration//GEN-END:variables
}
