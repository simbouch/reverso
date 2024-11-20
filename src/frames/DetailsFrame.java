/**
 * This class represents a frame that allows the
 * user to view, create, update or delete details
 * about a client or a prospect. This frame
 * provides text fields for the user to enter
 * information about a company, such as the
 * company name, address, number of employees,
 * email, and comments. The user can also select
 * the company type (client or prospect), and the
 * date of the prospecting. The frame includes
 * buttons for validating the user input,
 * returning to the previous frame, quitting the
 * application. The class provides constructors
 * that take different parameters, allowing the
 * frame to be created in different contexts, such
 * as for creating a new company or editing an
 * existing one. The class also provides methods
 * for validating the user input, updating the
 * company information, and displaying error
 * messages. This class extends the JFrame class.
 * @version 1.0
 * @since 2023-03-11
 * @author Bouchaib Khribech
 */
package frames;

import com.bouchaib.entity.*;
import com.bouchaib.dao.daoSql.ClientDao;
import com.bouchaib.dao.daoSql.ProspectDao;
import com.bouchaib.dao.daoSql.ReversoDaoException;
import com.bouchaib.entity.entityException.ReversoException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import utilities.CrudEnum;
import utilities.TypeSocieteEnum;

public class DetailsFrame extends JFrame {
    private JPanel detailsFrame;

    private JTextField numRueField;

    private JTextField nomRueField;

    private JTextField villeField;

    private JTextField nbEmployesField;

    private JTextField adresseMailField;

    private JTextField commentairesField;

    private JTextField chiffreAffairesField;

    private JTextField estInteresseField;

    private JButton validerButton2;

    private JButton revenirButton;

    private JTextField idtextField;

    private JTextField raisonSocialeField;

    private JTextField codePostaleField;

    private JTextField dateProspectionField;

    private JLabel idLabel;

    private JLabel raisonSocialeLabel;

    private JLabel numRueLabel;

    private JLabel nomRueLabel;

    private JLabel adresseLabel;

    private JLabel commentairesLabel;

    private JLabel chiffreAffairesLabel;

    private JLabel estInteresseLabel;

    private JLabel nbEmployesLabel;

    private JLabel dateProspectionLabel;

    private JTextField telephoneField;

    private JLabel telephoneLabel;

    private JLabel gestionLabel;

    private JButton quitterButton;

    private CrudEnum selectedCrud;

    private TypeSocieteEnum selectedTypeSociete;

    private Societe societe;
    private Prospect prospect;
    private  Client client;
    /**
     * Constructs a new DetailsFrame with the
     * specified parameters.
     * @param selectedCrud The CRUD operation to
     *     perform (CREATE, READ, UPDATE, or
     *     DELETE).
     * @param selectedTypeSociete The type of
     *     company (Client or Prospect).
     */
    public DetailsFrame(Societe societe,
                        CrudEnum selectedCrud,
                        TypeSocieteEnum selectedTypeSociete) {
        this.selectedTypeSociete = selectedTypeSociete;
        this.selectedCrud = selectedCrud;
        this.societe = societe;
        initcomponentDetailsFrame();
        buttonsDetailsFrame();
    }
    /**
     * Constructs a new DetailsFrame with the
     * specified CRUD operation and company type.
     * @param selectedCrud The CRUD operation to
     *     perform (CREATE, UPDATE, or DELETE).
     * @param selectedTypeSociete The type of
     *     company (Client or Prospect).
     */
    public DetailsFrame(CrudEnum selectedCrud,
                        TypeSocieteEnum selectedTypeSociete) {
        this.selectedTypeSociete = selectedTypeSociete;
        this.selectedCrud = selectedCrud;
        initcomponentDetailsFrame();
        buttonsDetailsFrame();
    }
    /**
     * Defines the behavior of buttons in the
     * details frame of a graphical user interface
     * (GUI). This method creates and assigns
     * various event listeners to respond to user
     * actions on the frame's buttons.
     *
     * The "Revenir" button creates a new
     * WelcomeFrame object and hides the current
     * DetailsFrame when clicked. The "Quitter"
     * button exits the program when clicked. The
     * "Valider" button performs different actions
     * depending on the value of the selectedCrud
     * field:
     *   - If selectedCrud is CREATE, this listener
     * attempts to create a new Prospect or Client
     * object based on the selectedTypeSociete
     * field. If successful, it displays a success
     * message and prompts the user to view the new
     * object in the list.
     *   - If selectedCrud is UPDATE, this listener
     * attempts to update an existing Prospect or
     * Client object based on the
     * selectedTypeSociete field. If successful, it
     * displays a success message and prompts the
     * user to view the updated object in the list.
     * @throws NumberFormatException If a number
     *     field contains invalid input.
     * @throws DateTimeParseException If the date
     *     input is not in the correct format.
     * @throws ReversoException If there is an error
     *     with input validation or database
     *     connection.
     * @throws Exception If an unexpected error
     *     occurs.
     */
    private void buttonsDetailsFrame() {
        revenirButton.addActionListener(new ActionListener() {
            /**
             * Responds to the action of clicking
             * the "Revenir" button by creating a
             * new WelcomFrame and hiding the
             * current DetailsFrame.
             *
             * @param e The ActionEvent that
             *     occurred.
             */
            @Override

            public void actionPerformed(ActionEvent e) {
                WelcomeFrame welcomFrame = new WelcomeFrame();
                detailsFrame.setVisible(false);
                dispose();
            }
        });
        quitterButton.addActionListener(new ActionListener() {
            /**
             * ActionListener for the "Quitter"
             * button on the DetailsFrame GUI. Exits
             * the program when the button is
             * clicked.
             *
             * @param e The ActionEvent that
             *     occurred.
             */
            @Override

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /**
         * ActionListener method for the "validerButton2" button.
         * Attempts to create, update, or delete a "Societe" object
         * based on the selected CRUD action and type of Societe. If
         * the operation is successful, displays a new
         * "WelcomeFrame" and disposes of the current frame.
         * @param e The ActionEvent that triggered the method.
         * @throws NumberFormatException If a non-numeric value is
         *     entered in the "Chiffre d'affaires" or "Nombre
         *     d'employés" fields.
         * @throws DateTimeParseException If the "date de
         *     prospection" field is not in the format "jj/MM/aaaa".
         * @throws ReversoException If an error occurs while using
         *     the Reverso API to translate text.
         * @throws Exception If any other error occurs.
         */
        validerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logger LOGGER = Logger.getLogger(getClass().getName());

                try {
                    switch (selectedCrud) {
                        case CREATE:
                            createSociete(selectedTypeSociete);
                            break;
                        case UPDATE:
                            performUpdateOperation();
                            break;
                        case DELETE:
                            performDeleteOperation();
                            break;
                    }
                    new WelcomeFrame();
                    dispose();
                } catch (ReversoException rex) {
                    JOptionPane.showMessageDialog(null, rex.getMessage());
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(null,
                            "Chiffre d'affaires et Nombre " +
                                    "d'employés doivent être des nombres.");
                } catch (DateTimeParseException dex) {
                    JOptionPane.showMessageDialog(null,
                            "Le format de la date de prospection "
                                    + "doit être jj/mm/aaaa.");
                } catch (ReversoDaoException rdaoex) {
                    int gravityLevel = rdaoex.getGravity();
                    if (gravityLevel == 1) {
                        JOptionPane.showMessageDialog(null,
                                rdaoex.getMessage(), "Information",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (gravityLevel == 5) {
                        JOptionPane.showMessageDialog(null,
                                rdaoex.getMessage(), "Erreur grave",
                                JOptionPane.ERROR_MESSAGE);
                        LOGGER.log(Level.SEVERE, rdaoex.getMessage(), rdaoex);
                        rdaoex.printStackTrace();
                        System.exit(1);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Une erreur s'est produite. " +
                                    "L'application va s'arrêter.");
                    LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });

    }
    /**
     * Updates the specified "Societe" object in the appropriate
     * list of prospects or clients. Displays a message dialog
     * to indicate that the operation was successful.
     *     updated.
    }*/
    private void performUpdateOperation() throws ReversoException, ReversoDaoException{
        societe.setRaisonSociale(raisonSocialeField.getText());
        societe.setNumRue(numRueField.getText());
        societe.setNomRue(nomRueField.getText());
        societe.setCodePostal(codePostaleField.getText());
        societe.setVille(villeField.getText());
        societe.setTelephone(telephoneField.getText());
        societe.setAdresseMail(adresseMailField.getText());
        societe.setCommentaires(commentairesField.getText());
        Logger LOGGER = Logger.getLogger(getClass().getName());

            if (societe instanceof Client) {
                double chiffreAffaires = Double.parseDouble(chiffreAffairesField.getText());
                int nbEmployes = Integer.parseInt(nbEmployesField.getText());
                ((Client) societe).setChiffreAffaires(chiffreAffaires);
                ((Client) societe).setNbEmployes(nbEmployes);
                ClientDao clientDao = new ClientDao();
                clientDao.save((Client) societe,selectedCrud);
            } else if (societe instanceof Prospect) {
                LocalDate dateProspection = LocalDate.parse(dateProspectionField.getText(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String estInteresse = estInteresseField.getText();
                ((Prospect) societe).setDateProspection(dateProspection);
                ((Prospect) societe).setEstInteresse(estInteresse);
                ProspectDao prospectDao = new ProspectDao();
                prospectDao.save((Prospect) societe,selectedCrud);
            }
            JOptionPane.showMessageDialog(null,
                    "La mise à jour a été effectuée avec succès.");
            dispose();
        }


    /**
     * Deletes the selected "Societe" object from the
     * appropriate list of prospects or clients. Displays a
     * confirmation dialog to confirm the delete operation. If
     * the user confirms the delete operation, removes the
     * selected Societe object and displays a message dialog to
     * indicate that the operation was successful. If the user
     * cancels the delete operation, displays a new
     * "WelcomeFrame" and disposes of the current frame.
     */
    private void performDeleteOperation() throws ReversoException,ReversoDaoException {
        JLabel messageLabel = new JLabel("Confirmez-vous la suppression?");
        messageLabel.setForeground(Color.RED);

        int confirmation = JOptionPane.showOptionDialog(null,
                messageLabel,
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);
        Logger LOGGER = Logger.getLogger(getClass().getName());

        if (confirmation == JOptionPane.YES_OPTION) {

                if (societe instanceof Prospect) {
                    ProspectDao prospectDao = new ProspectDao();
                    prospectDao.delete((Prospect) societe);
                } else if (societe instanceof Client) {
                    ClientDao clientDao = new ClientDao();
                    clientDao.delete((Client) societe);
                }
            JOptionPane.showMessageDialog(null,
                    "La suppression a été" +
                            " effectuée avec succès.");
            new WelcomeFrame();
            dispose();
        }
    }

    /**
     * Creates a new Societe object of the specified type
     * based on the values entered in the corresponding input
     * fields.
     *
     * @param selectedTypeSociete the type of Societe to be
     *     created
     * @return a new Societe object of the specified type
    }*/
    private void createSociete(
            TypeSocieteEnum selectedTypeSociete)
            throws ReversoException,
            ReversoDaoException {
        String raisonSociale =
                raisonSocialeField.getText();
        String numRue = numRueField.getText();
        String nomRue = nomRueField.getText();
        String codePostal =
                codePostaleField.getText();
        String ville = villeField.getText();
        String telephone =
                telephoneField.getText();
        String adresseMail =
                adresseMailField.getText();
        String commentaires =
                commentairesField.getText();
        Logger LOGGER =
                Logger.getLogger(getClass().getName());
        switch (selectedTypeSociete) {
            case ENUMCLIENT:
                double chiffreAffaires =
                        Double.parseDouble(
                                chiffreAffairesField
                                        .getText());
                int nbEmployes = Integer.parseInt(
                        nbEmployesField.getText());
                Client client = new Client(
                        raisonSociale, numRue, nomRue,
                        codePostal, ville, telephone,
                        adresseMail, commentaires,
                        chiffreAffaires, nbEmployes);
                ClientDao clientDao = new ClientDao();
                clientDao.save(client, selectedCrud);
                break;
            case ENUMPROSPECT:
                LocalDate dateProspection =
                        LocalDate.parse(
                                dateProspectionField.getText(),
                                DateTimeFormatter.ofPattern(
                                        "dd/MM/yyyy"));
                String estInteresse =
                        estInteresseField.getText();
                Prospect prospect = new Prospect(
                        raisonSociale, numRue, nomRue,
                        codePostal, ville, telephone,
                        adresseMail, commentaires,
                        dateProspection, estInteresse);
                ProspectDao prospectDao =
                        new ProspectDao();
                prospectDao.save(
                        prospect, selectedCrud);
                break;
            default:
                break;
        }
        JOptionPane.showMessageDialog(null,
                "La création a été effectuée avec succès!",
                "Création",
                JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Initializes the components of the DetailsFrame and sets
     * its properties based on the selected CRUD operation and
     * type of company. Sets the content pane to detailsFrame,
     * sets the title to "Gestion des prospects et clients",
     * sets the size to 600x800, and sets the default close
     * operation to JFrame.EXIT_ON_CLOSE. Depending on the
     * selectedTypeSociete parameter, certain fields and
     * labels are hidden or disabled. Depending on the
     * selectedCrud parameter, the gestionLabel and certain
     * fields are set with the appropriate text and values.
     * The frame is then set to be visible.
     */
    private void initcomponentDetailsFrame() {
        this.setContentPane(detailsFrame);
        setTitle("Gestion des prospects et clients");
        setSize(600, 800);
        Color customGrayColor = new Color(128, 128, 128);
        this.getContentPane().setBackground(customGrayColor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        switch (selectedTypeSociete) {
            case ENUMCLIENT:
                dateProspectionField.setEnabled(false);
                dateProspectionField.setVisible(false);
                estInteresseField.setVisible(false);
                estInteresseField.setEnabled(false);
                dateProspectionLabel.setEnabled(false);
                dateProspectionLabel.setVisible(false);
                estInteresseLabel.setEnabled(false);
                estInteresseLabel.setVisible(false);
                break;
            case ENUMPROSPECT:
                chiffreAffairesField.setVisible(false);
                chiffreAffairesField.setEnabled(false);
                nbEmployesField.setVisible(false);
                nbEmployesField.setEnabled(false);
                chiffreAffairesLabel.setVisible(false);
                chiffreAffairesLabel.setEnabled(false);
                nbEmployesLabel.setVisible(false);
                nbEmployesLabel.setEnabled(false);
                break;
        }
        switch (selectedCrud) {
            case CREATE:
                if (selectedTypeSociete
                        == TypeSocieteEnum.ENUMPROSPECT) {
                    gestionLabel.setText(
                            "Vous êtes en train de créer un prospect");
                } else if (selectedTypeSociete
                        == TypeSocieteEnum.ENUMCLIENT) {
                    gestionLabel.setText(
                            "Vous êtes en train de créer un client");
                }
                setupCreateComponents();
                break;
            case UPDATE:
                if (selectedTypeSociete
                        == TypeSocieteEnum.ENUMPROSPECT) {
                    gestionLabel.setText(
                            "Vous êtes en train de modifier un prospect");
                    setupUpdateComponents(societe);
                } else if (selectedTypeSociete
                        == TypeSocieteEnum.ENUMCLIENT) {
                    gestionLabel.setText(
                            "Vous êtes en train de modifier un client");
                    setupUpdateComponents(societe);
                }
                break;
            case DELETE:
                if (selectedTypeSociete
                        == TypeSocieteEnum.ENUMPROSPECT) {
                    gestionLabel.setText(
                            "Vous êtes en train de supprimer un prospect");
                    setupDeleteComponents((Prospect) societe);
                } else if (selectedTypeSociete
                        == TypeSocieteEnum.ENUMCLIENT) {
                    gestionLabel.setText(
                            "Vous êtes en train de supprimer un client");
                    setupDeleteComponents(societe);
                }
                break;
        }
    }
    /**
     * Sets up components for the CREATE operation.
     * Disables and hides the ID text field.
     */
    private void setupCreateComponents() {
        idtextField.setEditable(false);
        idtextField.setVisible(false);
    }
    /**
     * Sets up components for the UPDATE operation.
     * Disables the ID text field and populates the input
     * fields with the Societe's data. If the Societe is an
     * instance of Prospect, it populates the specific fields
     * for Prospect. If the Societe is an instance of Client,
     * it populates the specific fields for Client.
     *
     * @param societe The Societe object to be updated.
     */
    private void setupUpdateComponents(Societe societe) {
        idtextField.setEditable(false);
        idtextField.setEnabled(false);
        idtextField.setText(String.valueOf(societe.getId()));
        raisonSocialeField.setText(societe.getRaisonSociale());
        numRueField.setText(societe.getNumRue());
        nomRueField.setText(societe.getNomRue());
        codePostaleField.setText(societe.getCodePostal());
        villeField.setText(societe.getVille());
        telephoneField.setText(societe.getTelephone());
        adresseMailField.setText(societe.getAdresseMail());
        commentairesField.setText(societe.getCommentaires());

        if (societe instanceof Prospect) {
            Prospect choixProspect = (Prospect) societe;
            estInteresseField.setText(
                    choixProspect.getEstInteresse());
            dateProspectionField.setText(
                    choixProspect.getDateProspection().format(
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } else if (societe instanceof Client) {
            Client choixclient = (Client) societe;
            chiffreAffairesField.setText(
                    String.valueOf(choixclient.getChiffreAffaires()));
            nbEmployesField.setText(
                    String.valueOf(choixclient.getNbEmployes()));
        }
    }
    /**
     * Sets up components for the DELETE operation.
     * Disables and sets all input fields as non-editable,
     * then calls the setupUpdateComponents method to populate
     * the fields with the Societe's data.
     *
     * @param societe The Societe object to be deleted.
     */
    private void setupDeleteComponents(Societe societe) {
        idtextField.setEditable(false);
        idtextField.setEnabled(false);
        raisonSocialeField.setEditable(false);
        adresseMailField.setEditable(false);
        telephoneField.setEditable(false);
        commentairesField.setEditable(false);
        codePostaleField.setEditable(false);
        nomRueField.setEditable(false);
        numRueField.setEditable(false);
        estInteresseField.setEditable(false);
        dateProspectionField.setEditable(false);
        villeField.setEditable(false);
        chiffreAffairesField.setEditable(false);
        nbEmployesField.setEditable(false);
        setupUpdateComponents(societe);
    }
}