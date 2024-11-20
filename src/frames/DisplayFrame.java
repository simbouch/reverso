/**
 * The DisplayFrame class is a GUI that displays a
 * table of clients or prospects depending on the
 * value of the `typeSocieteEnum` parameter. It
 * extends the JFrame class and contains a JPanel
 * and JTable to display the data.
 *
 * @version 1.0
 * @author Bouchaib Khribech
 */
package frames;

import com.bouchaib.entity.Client;
import com.bouchaib.entity.Prospect;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.bouchaib.dao.daoSql.ClientDao;
import com.bouchaib.dao.daoSql.ProspectDao;
import com.bouchaib.dao.daoSql.ReversoDaoException;
import com.bouchaib.entity.entityException.ReversoException;
import utilities.CrudEnum;
import utilities.TypeSocieteEnum;

import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;

public class DisplayFrame extends JFrame {
    private JPanel displayFrame;

    private JTable table1;

    private JButton quitterButton;

    private JButton revenirButton;

    private JLabel aficherLabel;

    private TypeSocieteEnum typeSocieteEnum;

    private CrudEnum selectedCrud;
    private TypeSocieteEnum selectedtypesociete;
    /**
     * The constructor of the DisplayFrame class
     * initializes the typeSocieteEnum parameter and
     * calls the `remplissage()` and
     * `initcomponentDisplayFrame()` methods to
     * display the table.
     *
     * @param typeSocieteEnum the type of company
     *     data to display:
     */


    public DisplayFrame(
            TypeSocieteEnum typeSocieteEnum) {
        this.typeSocieteEnum = typeSocieteEnum;
        initcomponentDisplayFrame();
        fillJtableWithSociete();
    }

    /**
     The remplissage() method is used to populate
     the JTable with data depending on the type of
     company data to display, either clients or
     prospects. The method creates a
     DefaultTableModel object, sets its column names
     and adds the rows of data from either the list
     of prospects or clients. The method then sets
     the model for the table and adds an
     ActionListener to the quitterButton and
     revenirButton to handle disposing the
     DisplayFrame and creating a new WelcomFrame.
     */
    private void fillJtableWithSociete() {
        DefaultTableModel model = new DefaultTableModel();
        Object[] rowData;

        ClientDao clientDao = new ClientDao();
        ProspectDao prospectDao = new ProspectDao();
        List<Client> allClients = new ArrayList<>();
        List<Prospect> allProspects = new ArrayList<>();

        try {
            if (typeSocieteEnum == TypeSocieteEnum.ENUMPROSPECT) {
                allProspects = prospectDao.findAll();
            } else {
                allClients = clientDao.findAll();
            }
        } catch (ReversoException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(), "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ReversoDaoException e) {
            int gravityLevel = e.getGravity();
            if (gravityLevel == 1) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (gravityLevel == 5) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Erreur grave",
                        JOptionPane.ERROR_MESSAGE);
                LOGGER.log(
                        Level.SEVERE, e.getMessage(), e);
                e.printStackTrace();
                System.exit(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(), "Erreur grave",
                    JOptionPane.ERROR_MESSAGE);
            LOGGER.log(
                    Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
            System.exit(1);
        }
        if (typeSocieteEnum == TypeSocieteEnum.ENUMPROSPECT) {
            String[] columnNames = {"Identifiant", "Raison Sociale",
                    "Numéro de Rue", "Nom de Rue", "Code Postal",
                    "Ville", "Téléphone", "Adresse Email",
                    "Date de Prospection", "Intérêt"};
            model.setColumnIdentifiers(columnNames);
            Collections.sort(allProspects,
                    Comparator.comparing(Prospect::getRaisonSociale));
            SimpleDateFormat dateFormat =
                    new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
            for (Prospect prospect : allProspects) {
                Date dateProspection = Date.from(
                        prospect.getDateProspection()
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant());
                String formattedDate =
                        dateFormat.format(dateProspection);
                rowData = new Object[]{prospect.getId(),
                        prospect.getRaisonSociale(), prospect.getNumRue(),
                        prospect.getNomRue(), prospect.getCodePostal(),
                        prospect.getVille(), prospect.getTelephone(),
                        prospect.getAdresseMail(), formattedDate,
                        prospect.getEstInteresse()};
                model.addRow(rowData);
            }
        } else {
            String[] columnNames = {"", "Raison Sociale",
                    "Numéro Rue", "Nom Rue", "Code Postal", "Ville",
                    "Téléphone", "Adresse Email", "Chiffre d'affaires",
                    "Nombre d'employés"};
            model.setColumnIdentifiers(columnNames);
            Collections.sort(allClients,
                    Comparator.comparing(Client::getRaisonSociale));
            for (Client client : allClients) {
                rowData = new Object[]{client.getId(),
                        client.getRaisonSociale(), client.getNumRue(),
                        client.getNomRue(), client.getCodePostal(),
                        client.getVille(), client.getTelephone(),
                        client.getAdresseMail(),
                        client.getChiffreAffaires(),
                        client.getNbEmployes()};
                model.addRow(rowData);
            }
        }
        table1.setModel(model);

        quitterButton.addActionListener(
                new ActionListener() {
                    /**
                     * @param e Adds an ActionListener to
                     *     the
                     *          quitterButton that disposes
                     * of the DisplayFrame when clicked.
                     */
                    @Override

                    public void actionPerformed(
                            ActionEvent e) {
                        System.exit(0);
                    }
                });
        revenirButton.addActionListener(
                new ActionListener() {
                    /**
                     * @param e Adds an ActionListener to
                     *          creates a new WelcomFrame
                     * and disposes of the current
                     * DisplayFrame.
                     */
                    @Override

                    public void actionPerformed(
                            ActionEvent e) {
                        WelcomeFrame welcomFrame =
                                new WelcomeFrame();
                        dispose();
                    }
                });
    }
    /**

     The initcomponentDisplayFrame() method
     initializes the display frame by setting the
     content pane to displayFrame, setting the title
     to "Gestion des prospects et clients", setting
     the size to 600x800, and setting the default
     close operation to JFrame.EXIT_ON_CLOSE. The
     frame is also set to be visible. Depending on
     the typeSocieteEnum parameter, the aficherLabel
     is set to display the appropriate message.
     */
    private void initcomponentDisplayFrame() {
        this.setContentPane(displayFrame);
        setTitle("Gestion des prospects et clients");
        setSize(600, 800);
        Color customGrayColor = new Color(128, 128, 128);
        this.getContentPane().setBackground(customGrayColor);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        switch (typeSocieteEnum) {
            case ENUMPROSPECT:
                aficherLabel.setText(
                        "Vous affichez la liste des prospects !");
                break;
            case ENUMCLIENT:
                aficherLabel.setText(
                        "Vous affichez la liste des clients !");
                break;
        }

    }

}