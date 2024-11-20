/**

 This class represents a frame for displaying contracts
 associated with a specific client. It extends the JFrame
 class to create a graphical user interface.
 @author Khribech Bouchaib
 @version 1.0
 @since 2023-03-25
 */
package frames;
import com.bouchaib.entity.Client;
import com.bouchaib.entity.Contrat;
import com.bouchaib.dao.daoSql.ContratDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ContratsFrame extends JFrame {
    private JTable contractsTable;
    private JTable table1;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JPanel contratsFrame;
    private int selectedIndex = 0;
    private ContratDao contratDao;
    private Client client;
    /**

     Constructs a ContratsFrame with a given list of contracts
     and the associated client.

     @param client the Client associated with the contracts.
     */
    public ContratsFrame(Client client) {
        super("Contrats du client");
        this.client = client;
        initComponents();
        fillJtable();
        buttonsBehavior();
    }
    /**

     Initializes the components of the ContratsFrame.
     */
    private void initComponents() {
        setTitle("liste des contrats par client");
        setSize(600, 800);
        Color customGrayColor = new Color(128, 128, 128);
        this.getContentPane().setBackground(customGrayColor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Libellé du contrat");
        tableModel.addColumn("Montant du contrat en EURO");

        contractsTable = new JTable(tableModel);
        scrollPane = new JScrollPane(contractsTable);

        add(scrollPane, BorderLayout.CENTER);
    }
    /**
     Fills the table with the contracts information.
     */
    public void fillJtable() {
        JLabel clientLabel =
                new JLabel("La liste des Contrats associée à "
                        + client.getRaisonSociale() + " est:");
        add(clientLabel, BorderLayout.NORTH);
        tableModel.setRowCount(0);

        for (Contrat contrat : client.getContrats()) {
            Object[] rowData = {contrat.getLibelleContrat(),
                    contrat.getMontantContrat()};
            tableModel.addRow(rowData);
        }
    }

    /**

     Sets up the behavior of the buttons in the ContratsFrame.
     */
    private void buttonsBehavior() {
        JButton revenirButton =
                new JButton("Revenir à l'accueil");
        JButton quitterButton = new JButton("Quitter");

        revenirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeFrame();
                dispose();
            }
        });
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonsPanel =
                new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(revenirButton);
        buttonsPanel.add(quitterButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
