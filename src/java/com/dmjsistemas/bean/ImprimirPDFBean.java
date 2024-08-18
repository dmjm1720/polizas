package com.dmjsistemas.bean;

import com.dmjsistemas.dao.IRutasDao;
import com.dmjsistemas.impl.RutasDaoImpl;
import com.dmjsistemas.model.Factf01;
import com.dmjsistemas.model.TempRuta;
import com.dmjsistemas.util.Conexion;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.primefaces.PrimeFaces;

@Named(value = "imprimir")
@ViewScoped
public class ImprimirPDFBean extends Conexion implements Serializable {

    //**INICIA VARIABLES**//
    PDFMergerUtility merger = new PDFMergerUtility();
    private String pathPdfCobranza;
    private Date fec1;
    private Date fec2;
    private Factf01 f;
    private List<Factf01> lista;
    private Factf01 fac;
    private List<String> listaFacturas;
    private String filtroCliente;
    private List<String> listaCliente;
    List<String> listarTodo;

    private TempRuta tmpRuta;
    
    private ArrayList<String> newList;
    private String listaPendientes;

    //**TERMINA VARIABLES**//
    public ImprimirPDFBean() {
    }

    @PostConstruct
    public void Init() {
        merger = new PDFMergerUtility();
        this.pathPdfCobranza = null;
        f = new Factf01();
        lista = new ArrayList<>();
        listaFacturas = new ArrayList<>();
        fac = new Factf01();
        listaCliente = new ArrayList<>();
        listarTodo = new ArrayList();
        this.tmpRuta = new TempRuta();
        this.tmpRuta.setRutaTemp("/resources/archivoPDF.pdf");
        newList= new ArrayList<>();
    }

    //**INICIA GET Y SET**//
    public String getPathPdfCobranza() {
        return pathPdfCobranza;
    }

    public void setPathPdfCobranza(String pathPdfCobranza) {
        this.pathPdfCobranza = pathPdfCobranza;
    }

    public Date getFec1() {
        return fec1;
    }

    public void setFec1(Date fec1) {
        this.fec1 = fec1;
    }

    public Date getFec2() {
        return fec2;
    }

    public void setFec2(Date fec2) {
        this.fec2 = fec2;
    }

    public Factf01 getF() {
        return f;
    }

    public void setF(Factf01 f) {
        this.f = f;
    }

    public List<Factf01> getLista() {
        return lista;
    }

    public void setLista(List<Factf01> lista) {
        this.lista = lista;
    }

    public Factf01 getFac() {
        return fac;
    }

    public void setFac(Factf01 fac) {
        this.fac = fac;
    }

    public List<String> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<String> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public String getFiltroCliente() {
        return filtroCliente;
    }

    public void setFiltroCliente(String filtroCliente) {
        this.filtroCliente = filtroCliente;
    }

    public List<String> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<String> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<String> getListarTodo() {
        return listarTodo;
    }

    public void setListarTodo(List<String> listarTodo) {
        this.listarTodo = listarTodo;
    }

    public TempRuta getTmpRuta() {
        return tmpRuta;
    }

    public void setTmpRuta(TempRuta tmpRuta) {
        this.tmpRuta = tmpRuta;
    }

    public ArrayList<String> getNewList() {
        return newList;
    }

    public void setNewList(ArrayList<String> newList) {
        this.newList = newList;
    }

    public String getListaPendientes() {
        return listaPendientes;
    }

    public void setListaPendientes(String listaPendientes) {
        this.listaPendientes = listaPendientes;
    }

    
    
    //**TERMINA GET Y SET**//
    @SuppressWarnings("CallToPrintStackTrace")
    public void impresion() {
        // Directorio donde se encuentran los PDFs que quieres unir

        IRutasDao ruta = new RutasDaoImpl();
        pathPdfCobranza = ruta.pathPDFCobranza("ruta_pdf_cobranza");

        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");

        String currentYear = getYearFormat.format(fec1);

        String directorioEntrada = pathPdfCobranza + "/";
        //String directorioEntrada = pathPdfCobranza + "/" + currentYear + "";
        // Nombre del archivo de salida (PDF unido)
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String pathResources = servletContext.getRealPath("resources/");
        this.tmpRuta.setRutaTemp(pathResources + "/archivoPDF.pdf");
        String pdfExtension = ".pdf";

        //Borrar archivo previamente generado
        File archivoBorrar = new File(this.tmpRuta.getRutaTemp());

        if (archivoBorrar.exists()) {
            archivoBorrar.delete();
        }

        // Llamar al método para buscar archivos
        List<File> archivosEncontrados = buscarArchivos(new File(directorioEntrada), pdfExtension);

        try {
            // Mostrar los archivos encontrados
            System.out.println("Archivos encontrados:");
            List<String> listaOriginal = new ArrayList<>();
            List<String> listaArchivosEncontrados = new ArrayList<>();

            for (Factf01 factf01 : lista) {
                System.out.println("FACTURA: " + factf01.getCveDoc());
                listaOriginal.add(factf01.getCveDoc());
                for (File file : archivosEncontrados) {

                    if (file.toString().contains(factf01.getCveDoc() + pdfExtension)) {
                        System.out.println(file.getName() + " | " + factf01.getNombreProveedor());
                        listaArchivosEncontrados.add(factf01.getCveDoc());
                        merger.addSource(file);
                    }
                }
            }

            // Configurar el archivo de salida
            merger.setDestinationFileName(tmpRuta.getRutaTemp());
            // Fusionar los PDFs
            merger.mergeDocuments(MemoryUsageSetting.setupMixed(400000000));
            System.out.println("PDFs fusionados correctamente.");
           
            for (String element : listaOriginal) {
                if (!listaArchivosEncontrados.contains(element)) {
                    newList.add(element);
                }
            }
            //PrimeFaces.current().executeScript("PF(dlg2).show()");
            System.out.println("ARCHIVOS NO ENCONTRADOS: " + newList);
            this.listaPendientes = newList.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> buscarArchivos(File directorio, String extension) {
        List<File> archivosEncontrados = new ArrayList<>();

        // Verificar si el parámetro directorio es un directorio válido
        if (directorio.isDirectory()) {
            // Obtener la lista de archivos y subdirectorios dentro del directorio
            File[] archivos = directorio.listFiles();

            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        // Si es un directorio, llamar recursivamente al método para buscar dentro de él
                        archivosEncontrados.addAll(buscarArchivos(archivo, extension));
                    } else if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(extension.toLowerCase())) {
                        // Si es un archivo y tiene la extensión buscada, añadirlo a la lista
                        archivosEncontrados.add(archivo);
                    }
                }
            }
        }

        return archivosEncontrados;
    }

    public void listarFacturasPendientes() {
        lista = new ArrayList<>();
        lista.clear();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String f1 = dateFormat.format(fec1);
        String f2 = dateFormat.format(fec2);
        try {
            ConectarSae();
            Statement stFactf1 = getCnSae().createStatement();
            String sqlFactf1 = "SELECT "
                    + "F.TIP_DOC, F.CVE_DOC, F.CVE_CLPV, F.STATUS, F.FECHA_DOC, F.FECHA_VEN, F.CAN_TOT, F.IMP_TOT1, F.IMP_TOT4, F.CVE_OBS, F.NUM_ALMA, F.ACT_CXC, F.ACT_COI, F.ENLAZADO, "
                    + "F.TIP_DOC_E, F.NUM_MONED, F.TIPCAMB, F.NUM_PAGOS, F.PRIMERPAGO, F.RFC, F.CTLPOL, F.ESCFD, F.AUTORIZA, F.SERIE, F.FOLIO, F.DAT_ENVIO, F.CONTADO, F.CVE_BITA, F.BLOQ, F.FORMAENVIO, "
                    + "F.DES_FIN_PORC, F.DES_TOT_PORC, F.IMPORTE, F.COM_TOT_PORC, F.TIP_DOC_ANT, F.DOC_ANT, C.NOMBRE, CT.DOCTO, CT.FECHA_APLI, CT.REF_SIST, CT.CVE_CLIE "
                    + "FROM FACTF01 F "
                    + "INNER JOIN "
                    + "CUEN_DET01 CT ON F.CVE_DOC = CT.NO_FACTURA "
                    + "INNER JOIN "
                    + "CLIE01 C ON F.CVE_CLPV = C.CLAVE "
                    + "WHERE F.STATUS <> 'C' AND CT.REF_SIST='B' AND CT.FECHA_APLI BETWEEN '" + f1 + "' AND '" + f2 + "' ORDER BY CT.CVE_CLIE, CT.FECHA_APLI ASC";
            ResultSet rsFactf1 = stFactf1.executeQuery(sqlFactf1);
            if (!rsFactf1.isBeforeFirst()) {
            } else {
                while (rsFactf1.next()) {
                    f = new Factf01();
                    f.setTipDoc(rsFactf1.getString("TIP_DOC"));
                    f.setCveDoc(rsFactf1.getString("CVE_DOC"));
                    f.setCveClpv(rsFactf1.getString("CVE_CLPV"));
                    f.setStatus(rsFactf1.getString("STATUS"));
                    f.setFechaDoc(rsFactf1.getDate("FECHA_DOC"));
                    f.setFechaVen(rsFactf1.getDate("FECHA_VEN"));
                    f.setCanTot(rsFactf1.getDouble("CAN_TOT"));
                    f.setImpTot1(rsFactf1.getDouble("IMP_TOT1"));
                    f.setImpTot4(rsFactf1.getDouble("IMP_TOT4"));
                    f.setCveObs(rsFactf1.getLong("CVE_OBS"));
                    f.setNumAlma(rsFactf1.getLong("NUM_ALMA"));
                    f.setActCxc(rsFactf1.getString("ACT_CXC"));
                    f.setActCoi(rsFactf1.getString("ACT_COI"));
                    f.setEnlazado(rsFactf1.getString("ENLAZADO"));
                    f.setTipDocE(rsFactf1.getString("TIP_DOC_E"));
                    f.setNumMoned(rsFactf1.getLong("NUM_MONED"));
                    f.setTipcamb(rsFactf1.getDouble("TIPCAMB"));
                    f.setNumPagos(rsFactf1.getLong("NUM_PAGOS"));
                    f.setPrimerpago(rsFactf1.getDouble("PRIMERPAGO"));
                    f.setRfc(rsFactf1.getString("RFC"));
                    f.setCtlpol(rsFactf1.getLong("CTLPOL"));
                    f.setEscfd(rsFactf1.getString("ESCFD"));
                    f.setAutoriza(rsFactf1.getLong("AUTORIZA"));
                    f.setSerie(rsFactf1.getString("SERIE"));
                    f.setFolio(rsFactf1.getLong("FOLIO"));
                    f.setDatEnvio(rsFactf1.getLong("DAT_ENVIO"));
                    f.setContado(rsFactf1.getString("CONTADO"));
                    f.setCveBita(rsFactf1.getLong("CVE_BITA"));
                    f.setBloq(rsFactf1.getString("BLOQ"));
                    f.setFormaenvio(rsFactf1.getString("FORMAENVIO"));
                    f.setDesFinPorc(rsFactf1.getDouble("DES_FIN_PORC"));
                    f.setDesTotPorc(rsFactf1.getDouble("DES_TOT_PORC"));
                    f.setImporte(rsFactf1.getDouble("IMPORTE"));
                    f.setComTotPorc(rsFactf1.getDouble("COM_TOT_PORC"));
                    f.setTipDoc(rsFactf1.getString("TIP_DOC_ANT"));
                    f.setDocAnt(rsFactf1.getString("DOC_ANT"));
                    f.setNombreProveedor(rsFactf1.getString("NOMBRE"));
                    f.setNoPoliza(rsFactf1.getString("DOCTO"));
                    f.setFechaPago(rsFactf1.getDate("FECHA_APLI"));
                    lista.add(f);
                }
            }
            getLista(); //OBTENER LA LISTA DE LA CONSULTA
            impresion(); //IMPRIMIR LAS FACTURAS DE LA CONSULTA
            CerrarSae();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
