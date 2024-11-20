/**
 * The WelcomeFrame class is a GUI that
 * allows users to manage prospects and
 * clients for a business. It contains
 * buttons to navigate to different
 * management options, such as
 * displaying, creating, modifying, and
 * deleting clients or prospects.
 *
 * This class extends the JFrame class
 * and implements the ActionListener
 * interface to handle user input
 * events.
 *
 * @author Bouchaib Khribech
 * @version 1.0
 */
package frames;
import static com.bouchaib.entity.loggers.MyLoggersClass.LOGGER;
import static utilities.CrudEnum.CREATE;
import static utilities.TypeSocieteEnum.ENUMCLIENT;

import com.bouchaib.entity.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import com.bouchaib.dao.daoSql.ClientDao;
import com.bouchaib.dao.daoSql.ProspectDao;
import com.bouchaib.dao.daoSql.ReversoDaoException;
import com.bouchaib.entity.entityException.ReversoException;
import utilities.CrudEnum;
import utilities.TypeSocieteEnum;

public class WelcomeFrame extends JFrame  {
    private JButton prospectButton;
    private JButton clientButton;
    private JButton afficherButton;
    private JButton suprimerButton;
    private JButton modifierButton;
    private JButton creerButton;
    private JComboBox societeComboBox;
    private JPanel welcomeFrame;
    private JLabel gestionLabel;
    private JButton quitterButton;
    private JButton revenirButton;
    private JLabel welcomingLabel;
    private JButton validerButten;
    private JLabel choisirLabel;
    private JButton contractsButton;
    private JButton SeeContractsButton;
    private JLabel imageLabel;
    private TypeSocieteEnum selectedTypeSociete;
    private CrudEnum selectedCrud;
    private Societe societe;
    /**
     * Constructs a new WelcomeFrame
     * object by calling the initcomponent
     * and buttons methods.
     */
    public WelcomeFrame() {
        initcomponentWelcomeFrame();
        buttons();
    }
    /**
     * method Sets up the event listeners for the
     * buttons in the WelcomeFrame.
     */
    private void buttons() {
        revenirButton.addActionListener(
                new ActionListener() {
                    /**
                     * Invoked when the "Revenir" button is
                     * clicked. Creates a new instance of
                     * WelcomeFrame and disposes of the
                     * current window.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        WelcomeFrame welcomFrame =
                                new WelcomeFrame();
                        dispose();
                    }
                });
        quitterButton.addActionListener(
                new ActionListener() {
                    /**
                     * @param e Adds an
                     *     ActionListener to the
                     *     quitterButton
                     *          that disposes of
                     * the current window when the
                     * button is pressed.
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        System.exit(0);
                    }
                });
        clientButton.addActionListener(
                new ActionListener() {
                    /**
                     * Overrides the
                     * actionPerformed method of
                     * the ActionListener
                     * interface to handle the
                     * event triggered by the
                     * associated component. This
                     * method sets the visibility
                     * of certain buttons and a
                     * combo box, changes the text
                     * of a label, and updates the
                     * options in the combo box
                     * based on the list of all
                     * Clients.
                     *
                     * @param e Adds an
                     *     ActionListener to the
                     *     clientButton
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        selectedTypeSociete =
                                ENUMCLIENT;
                        afficherButton.setVisible(true);
                        creerButton.setVisible(true);
                        modifierButton.setVisible(true);
                        suprimerButton.setVisible(true);
                        societeComboBox.setVisible(false);
                        revenirButton.setVisible(true);
                        choisirLabel.setVisible(false);
                        validerButten.setVisible(false);
                        gestionLabel.setText("Vous êtes en" +
                                "train de gérer un client!");
                        clientButton.setForeground(Color.RED);
                        gestionLabel.setForeground(Color.white);
                        welcomingLabel.setVisible(false);
                        contractsButton.setVisible(true);
                        prospectButton.setVisible(false);
                        societeComboBox.removeAllItems();
                        fillComboBox(societeComboBox, TypeSocieteEnum.ENUMCLIENT);
                        }

                });
        prospectButton.addActionListener(
                new ActionListener() {
                    /**
                     * Overrides the
                     * actionPerformed method of
                     * the ActionListener
                     * interface to handle the
                     * event triggered by the
                     * associated component. This
                     * method sets the visibility
                     * of certain buttons and a
                     * combo box, changes the text
                     * of a label, and updates
                     * theoptions in the combo box
                     * based on the list of all
                     * prospects.
                     *
                     * @param e the ActionEvent
                     *     object that triggered
                     *     the event
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        selectedTypeSociete =
                                TypeSocieteEnum.ENUMPROSPECT;
                        JButton[] buttons = {afficherButton, creerButton,
                                modifierButton, suprimerButton, revenirButton,
                                clientButton};
                        JComboBox comboBox = societeComboBox;
                        JLabel[] labels = {choisirLabel, welcomingLabel};
                        JButton[] validationButtons = {validerButten};

                        for (JButton button : buttons) {
                            button.setVisible(true);
                        }

                        comboBox.setVisible(false);
                        for (JLabel label : labels) {
                            label.setVisible(false);
                        }
                        for (JButton validationButton : validationButtons) {
                            validationButton.setVisible(false);
                        }
                        gestionLabel.setText("Vous êtes en" +
                                "train de gérer un prospect!");
                        gestionLabel.setForeground(Color.white);
                        prospectButton.setForeground(Color.RED);
                        clientButton.setVisible(false);
                        contractsButton.setVisible(false);
                        setForeground(Color.RED);
                        societeComboBox.removeAllItems();
                        fillComboBox(societeComboBox, TypeSocieteEnum.ENUMPROSPECT);
                        }

                });
        afficherButton.addActionListener(
                new ActionListener() {
                    /**
                     * Overrides the actionPerformed method
                     * of the ActionListener interface to
                     * handle the event triggered by the
                     * associated component. This method
                     * creates a new DisplayFrame object
                     * with the selected CrudEnum and
                     * TypeSocieteEnum options and sets its
                     * visibility to true. The current frame
                     * is then disposed.
                     * @param e the ActionEvent object that
                     *     triggered the event
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        DisplayFrame displayFrame =
                                new DisplayFrame(selectedTypeSociete);
                        displayFrame.setVisible(true);
                        dispose();
                    }
                });
        creerButton.addActionListener(
                new ActionListener() {
                    /**
                     * Overrides the actionPerformed method
                     * of the ActionListener interface to
                     * handle the event triggered by the
                     * associated component. This method
                     * sets the selectedCrud instance
                     * variable to the CREATE value of the
                     * CrudEnum enum, sets the
                     * typeSocieteEnum variable to the value
                     * of the selectedTypeSociete instance
                     * variable, creates a new DetailsFrame
                     * object, and sets it to visible while
                     * disposing of the current frame.
                     * @param e the ActionEvent object that
                     *     triggered the event
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        selectedCrud = CrudEnum.CREATE;
                        TypeSocieteEnum typeSocieteEnum =
                                selectedTypeSociete;
                        DetailsFrame detailsFrame =
                                new DetailsFrame(
                                        CREATE, selectedTypeSociete);
                        detailsFrame.setVisible(true);
                        dispose();
                    }
                });
        modifierButton.addActionListener(
                new ActionListener() {
                    /**
                     * Adds an ActionListener to the
                     * modifierButton that sets the
                     * selectedCrud to UPDATE, sets the
                     * visibility of societeComboBox and
                     * validerButten to true, sets the
                     * visibility of choisirLabel to true
                     * and sets the text of choisirLabel to
                     * "Choisir et valider".
                     *
                     * @param e the ActionEvent object that
                     *          triggered the event
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        selectedCrud = CrudEnum.UPDATE;
                        societeComboBox.setVisible(true);
                        validerButten.setVisible(true);
                        choisirLabel.setVisible(true);
                        afficherButton.setVisible(false);
                        creerButton.setVisible(false);
                        contractsButton.setVisible(false);
                        suprimerButton.setVisible(false);
                        choisirLabel.setForeground(Color.WHITE);
                        choisirLabel.setText(
                                "Choisir et valider");
                    }
                });
        suprimerButton.addActionListener(
                new ActionListener() {
                    /**
                     * ActionListener for the
                     * suprimerButton. Overrides the
                     * actionPerformed method of the
                     * ActionListener interface to handle
                     * the event triggered by the associated
                     * component. This method sets the
                     * visibility of the societeComboBox and
                     * validerButten, changes the text of
                     * the choisirLabel, and assigns the
                     * selectedCrud value to
                     * CrudEnum.DELETE. When the user clicks
                     * on the button, the DetailsFrame will
                     * be launched with the option to delete
                     * a prospect or client.
                     * @param e the ActionEvent object that
                     *     triggered the event
                     */

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        selectedCrud = CrudEnum.DELETE;
                        societeComboBox.setVisible(true);
                        validerButten.setVisible(true);
                        choisirLabel.setVisible(true);
                        afficherButton.setVisible(false);
                        contractsButton.setVisible(false);
                        creerButton.setVisible(false);
                        modifierButton.setVisible(false);
                        choisirLabel.setForeground(Color.WHITE);
                        choisirLabel.setText(
                                "Sélectionner et Valider.");
                    }
                });
        validerButten.addActionListener(
                new ActionListener() {
                    /**
                     * Adds an ActionListener to the
                     * validerButten button that constructs
                     * a new DetailsFrame object and makes
                     * it visible, disposing of the current
                     * window when the button is pressed.
                     * @param e the ActionEvent object that
                     *     triggered
                     *        the event
                     */
                    @Override
                    public void actionPerformed(
                            ActionEvent e) {
                        ClientDao clientDao =
                                new ClientDao();
                        ProspectDao prospectDao =
                                new ProspectDao();
                        int selectedIndex =
                                societeComboBox
                                        .getSelectedIndex();

                        try {
                            switch (selectedTypeSociete) {
                                case ENUMCLIENT:
                                    societe =
                                            clientDao.findAll().get(
                                                    selectedIndex);
                                    DetailsFrame detailsFrame =
                                            new DetailsFrame(societe,
                                                    selectedCrud,
                                                    selectedTypeSociete);

                                    detailsFrame.setVisible(true);
                                    dispose();
                                    break;
                                case ENUMPROSPECT:
                                    societe =
                                            prospectDao.findAll().get(
                                                    selectedIndex);
                                    detailsFrame =
                                            new DetailsFrame(societe,
                                                    selectedCrud,
                                                    selectedTypeSociete);
                                    detailsFrame.setVisible(true);
                                    dispose();
                                    break;
                            }
                        } catch (ReversoException re) {
                            JOptionPane.showMessageDialog(null,
                                    re.getMessage(), "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                        } catch (ReversoDaoException rde) {
                            int gravityLevel =
                                    rde.getGravity();
                            if (gravityLevel == 1) {
                                JOptionPane.showMessageDialog(
                                        null, rde.getMessage(),
                                        "Information",
                                        JOptionPane
                                                .INFORMATION_MESSAGE);
                            } else if (gravityLevel == 5) {
                                JOptionPane.showMessageDialog(
                                        null, rde.getMessage(),
                                        "Erreur grave",
                                        JOptionPane.ERROR_MESSAGE);
                                LOGGER.log(Level.SEVERE,
                                        rde.getMessage(), rde);
                                rde.printStackTrace();
                                System.exit(1);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    ex.getMessage(),
                                    "Erreur grave",
                                    JOptionPane.ERROR_MESSAGE);
                            LOGGER.log(Level.SEVERE,
                                    ex.getMessage(), ex);
                            ex.printStackTrace();
                            System.exit(1);
                        }
                    }
                });
        contractsButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                societeComboBox.setVisible(true);
                validerButten.setVisible(false);
                choisirLabel.setVisible(true);
                afficherButton.setVisible(false);
                creerButton.setVisible(false);
                modifierButton.setVisible(false);
                SeeContractsButton.setVisible(true);
                suprimerButton.setVisible(false);
                contractsButton.setVisible(false);
                choisirLabel.setForeground(Color.WHITE);
                choisirLabel.setText("Sélectionnez pour afficher les contrats:");
            }
        });
        SeeContractsButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = societeComboBox.getSelectedIndex();
                try {
                    showContrats(selectedIndex);
                } catch (ReversoException re) {
                    JOptionPane.showMessageDialog(null,
                            re.getMessage(), "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ReversoDaoException rde) {
                    int gravityLevel = rde.getGravity();
                    if (gravityLevel == 1) {
                        JOptionPane.showMessageDialog(null,
                                rde.getMessage(), "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (gravityLevel == 5) {
                        JOptionPane.showMessageDialog(null,
                                rde.getMessage(), "Erreur grave",
                                JOptionPane.ERROR_MESSAGE);
                        LOGGER.log(
                                Level.SEVERE, rde.getMessage(), e);
                        rde.printStackTrace();
                        System.exit(1);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            ex.getMessage(), "Erreur grave",
                            JOptionPane.ERROR_MESSAGE);
                    LOGGER.log(
                            Level.SEVERE, ex.getMessage(), e);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }

    private void showContrats(int selectedIndex) throws ReversoException, ReversoDaoException {
        ClientDao clientDao = new ClientDao();
        Client client = clientDao.find(selectedIndex + 1);
        ArrayList<Contrat> contrats = client.getContrats();
        if (contrats.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Aucun contrat associé à ce client.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ContratsFrame contratsFrame = new ContratsFrame(client);
            contratsFrame.setVisible(true);
        }
    }
    /**
     * Sets up the initial components of the
     * WelcomeFrame. These include setting
     * the content pane to the welcomeFrame
     * JPanel, setting the title of the
     * frame to "Gestion des prospects et
     * clients", setting the size of the
     * frame to 600x800, setting the default
     * close operation to exit on close, and
     * making the frame visible.
     */
    private void initcomponentWelcomeFrame() {
        this.setContentPane(welcomeFrame);
        Color customGrayColor = Color.BLACK;
        this.getContentPane().setBackground(customGrayColor);
        setTitle("Gestion des prospects et clients");
        setSize(600, 800);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        JButton[] buttons = {afficherButton, creerButton,
                modifierButton, suprimerButton, revenirButton,
                validerButten,SeeContractsButton};
        JComboBox comboBox = societeComboBox;

        for (JButton button : buttons) {
            button.setVisible(false);
        }
        comboBox.setVisible(false);
        contractsButton.setVisible(false);
    }
    private void fillComboBox(
            JComboBox<String> societeComboBox,
            TypeSocieteEnum typeSocieteEnum) {
        ClientDao clientDao = new ClientDao();
        ArrayList<Client> clients;
        Logger LOGGER =
                Logger.getLogger(getClass().getName());

        try {
            clients = clientDao.findAll();
            if (typeSocieteEnum
                    == TypeSocieteEnum.ENUMCLIENT) {
                for (Client client : clients) {
                    societeComboBox.addItem(
                            client.getRaisonSociale());
                }
            } else if (typeSocieteEnum
                    == TypeSocieteEnum.ENUMPROSPECT) {
                ProspectDao prospectDao =
                        new ProspectDao();
                ArrayList<Prospect> prospects =
                        prospectDao.findAll();
                for (Prospect prospect : prospects) {
                    societeComboBox.addItem(
                            prospect.getRaisonSociale());
                }
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
    }
}



