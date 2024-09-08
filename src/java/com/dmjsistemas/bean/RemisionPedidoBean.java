package com.dmjsistemas.bean;

import com.dmjsistemas.dao.IEncabezadosRDao;
import com.dmjsistemas.dao.IPartidasRDao;
import com.dmjsistemas.dao.IRutasDao;
import com.dmjsistemas.impl.EncabezadoRecepcionImpl;
import com.dmjsistemas.impl.PartidasRDaoImpl;
import com.dmjsistemas.impl.RutasDaoImpl;
import com.dmjsistemas.model.EncabezadosRecepcion;
import com.dmjsistemas.model.Factf01;
import com.dmjsistemas.model.PartidasRecepcion;
import com.dmjsistemas.model.TempRuta;
import com.dmjsistemas.util.Conexion;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.bolivia.qulqi.Qulqi;
import org.bolivia.qulqi.Qulqi$COIN;
import org.bolivia.qulqi.Qulqi$FLOATING;
import org.primefaces.PrimeFaces;

@Named(value = "remisionPedidoBean")
@ViewScoped
public class RemisionPedidoBean extends Conexion implements Serializable {

    private Date fec1;
    private Date fec2;
    private EncabezadosRecepcion er;
    private List<EncabezadosRecepcion> listaVFR;
    private List<EncabezadosRecepcion> listaVF2;
    private List<PartidasRecepcion> listaPR;
    private PartidasRecepcion pRecep;

    private String pathPdfCobranza;
    PDFMergerUtility merger = new PDFMergerUtility();
    private TempRuta tmpRuta;
    private List<Factf01> lista;
    List<EncabezadosRecepcion> listaImpresion = new ArrayList<>();
    private String resultadoRFC;
    private String filterRFC;

    public RemisionPedidoBean() {

    }

    @PostConstruct
    public void init() {
        er = new EncabezadosRecepcion();
        listaVFR = new ArrayList<>();
        listaVF2 = new ArrayList<>();
        listaPR = new ArrayList<>();
        pRecep = new PartidasRecepcion();

        this.pathPdfCobranza = null;
        merger = new PDFMergerUtility();
        this.tmpRuta = new TempRuta();
        this.tmpRuta.setRutaTemp("/resources/archivoFRP.pdf");
        lista = new ArrayList<>();
        listaImpresion = new ArrayList<>();
        this.resultadoRFC = null;
        this.filterRFC = null;
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

    public EncabezadosRecepcion getEr() {
        return er;
    }

    public void setEr(EncabezadosRecepcion er) {
        this.er = er;
    }

    public List<EncabezadosRecepcion> getListaVFR() {
        return listaVFR;
    }

    public void setListaVFR(List<EncabezadosRecepcion> listaVFR) {
        this.listaVFR = listaVFR;
    }

    public List<EncabezadosRecepcion> getListaVF2() {
        return listaVF2;
    }

    public void setListaVF2(List<EncabezadosRecepcion> listaVF2) {
        this.listaVF2 = listaVF2;
    }

    public List<PartidasRecepcion> getListaPR() {
        return listaPR;
    }

    public void setListaPR(List<PartidasRecepcion> listaPR) {
        this.listaPR = listaPR;
    }

    public PartidasRecepcion getpRecep() {
        return pRecep;
    }

    public void setpRecep(PartidasRecepcion pRecep) {
        this.pRecep = pRecep;
    }

    public String getPathPdfCobranza() {
        return pathPdfCobranza;
    }

    public void setPathPdfCobranza(String pathPdfCobranza) {
        this.pathPdfCobranza = pathPdfCobranza;
    }

    public PDFMergerUtility getMerger() {
        return merger;
    }

    public void setMerger(PDFMergerUtility merger) {
        this.merger = merger;
    }

    public TempRuta getTmpRuta() {
        return tmpRuta;
    }

    public void setTmpRuta(TempRuta tmpRuta) {
        this.tmpRuta = tmpRuta;
    }

    public List<Factf01> getLista() {
        return lista;
    }

    public void setLista(List<Factf01> lista) {
        this.lista = lista;
    }

    public String getResultadoRFC() {
        return resultadoRFC;
    }

    public void setResultadoRFC(String resultadoRFC) {
        this.resultadoRFC = resultadoRFC;
    }

    public String getFilterRFC() {
        return filterRFC;
    }

    public void setFilterRFC(String filterRFC) {
        this.filterRFC = filterRFC;
    }

    public void insertarInformacion() throws SQLException, InterruptedException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String f1 = dateFormat.format(fec1);
        String f2 = dateFormat.format(fec2);

        listaImpresion = new ArrayList<>();
        EncabezadosRecepcion erImpresion = new EncabezadosRecepcion();

        //BUSCAR LAS REMISIONES ENCABEZADOS
        IEncabezadosRDao listaEncabezadosR = new EncabezadoRecepcionImpl();
        listaVFR = listaEncabezadosR.obtenerInfoEncabezadosRecepcion(f1, f2, filterRFC);

        //VALIDAR SI EXISTE LA FACTURA
        for (int i = 0; i < listaVFR.size(); i++) {

            IEncabezadosRDao validar = new EncabezadoRecepcionImpl();
            listaVF2 = new ArrayList<>();
            listaVF2 = validar.listaFacturaExiste(listaVFR.get(i).getCveDoc());

            System.out.println("VALIDACIÓN DE FACTURA" + listaVFR.get(i).getCveDoc() + " | EN BD: " + listaVF2.size());

            IEncabezadosRDao listaFRP = new EncabezadoRecepcionImpl();
            List<EncabezadosRecepcion> l = new ArrayList<>();
            l = listaFRP.listaFRP(listaVFR.get(i).getCveDoc());

            if (listaVF2.isEmpty()) {
                IEncabezadosRDao encabezadoR = new EncabezadoRecepcionImpl();

                IEncabezadosRDao validarOBS = new EncabezadoRecepcionImpl();

                //INSERTAR EL ENCABEZADO
                er = new EncabezadosRecepcion(listaVFR.get(i).getCveDoc(), listaVFR.get(i).getDocAnt(), listaVFR.get(i).getCveClpv(),
                        listaVFR.get(i).getFechaApli(), listaVFR.get(i).getCanTot(), listaVFR.get(i).getRfc(), listaVFR.get(i).getNombre(),
                        listaVFR.get(i).getCalle(), listaVFR.get(i).getColonia(), listaVFR.get(i).getEstado(), listaVFR.get(i).getCodigo(),
                        listaVFR.get(i).getTelefono(), validarOBS.obtenerInfoOBS(listaVFR.get(i).getStrObs()), listaVFR.get(i).getNumext(),
                        listaVFR.get(i).getColonia(), listaVFR.get(i).getMunicipio(), importeLetras(listaVFR.get(i).getCanTot().toString()).toUpperCase());

                encabezadoR.insertarEncabezadoRecepcion(er);

                //INSERTAR LAS PARTIDAS
                IPartidasRDao pr = new PartidasRDaoImpl();
                listaPR = new ArrayList<>();
                listaPR = pr.listaPartidasRecepcion(listaVFR.get(i).getDocAnt());

                for (int j = 0; j < listaPR.size(); j++) {
                    IPartidasRDao obs = new PartidasRDaoImpl();
                    pRecep = new PartidasRecepcion(listaPR.get(j).getDocAnt(), listaPR.get(j).getCveDoc(), listaPR.get(j).getFechaEnt(), listaPR.get(j).getELtpd(),
                            listaPR.get(j).getCveArt(), listaPR.get(j).getCant(), listaPR.get(j).getUniVenta(), listaPR.get(j).getDescr(), listaPR.get(j).getCtrlAlm(),
                            obs.buscarLote(listaPR.get(j).getELtpd()), listaPR.get(j).getPrec(), listaPR.get(j).getTotPartida());
                    IPartidasRDao insertarPR = new PartidasRDaoImpl();
                    insertarPR.insertarPartidaRecpcion(pRecep);
                }

            }

            IEncabezadosRDao listaFRP_PDF = new EncabezadoRecepcionImpl();
            List<EncabezadosRecepcion> listaPDF = new ArrayList<>();
            listaPDF = listaFRP_PDF.listaFRP_PDF(listaVFR.get(i).getCveDoc());

            System.out.println("____________________________________________");
            for (int k = 0; k < listaPDF.size(); k++) {

                //System.out.println("DATOS DE FACTURA, REMISIÓN Y PEDIDO: " + l.get(k).getCveDoc());
                erImpresion.setCveDoc(listaPDF.get(k).getCveDoc());

                System.out.println("ER: " + erImpresion.getCveDoc());
                listaImpresion.add(erImpresion);
                erImpresion = new EncabezadosRecepcion();
            }
            System.out.println("____________________________________________");

        }

        impresion(listaImpresion);

    }

    public String importeLetras(String importe) {

        Qulqi impLetras = new Qulqi();
        impLetras.setDecimalPartVisible(true);//parte decimal .00
        impLetras.setCoin(Qulqi$COIN.peso_mexicano);//moneda
        impLetras.setFloating(Qulqi$FLOATING.POINT);//punto flotante (.) o (,)
        System.out.println("IMPORTE EN LETRA: ***" + impLetras.showMeTheMoney(importe.toUpperCase()) + "***");

        return impLetras.showMeTheMoney(importe);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void impresion(List<EncabezadosRecepcion> lista) throws InterruptedException {
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
        this.tmpRuta.setRutaTemp(pathResources + "/archivoFRP.pdf");
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

            for (EncabezadosRecepcion er : lista) {
                System.out.println("FACTURA: " + er.getCveDoc() + pdfExtension);

                for (File file : archivosEncontrados) {

                    if (file.getName().equals(er.getCveDoc() + pdfExtension)) {
                        System.out.println(file.getName());
                        merger.addSource(file);
                    }
                }
            }

            String info = "Se han generado el archivo exitosamente...";
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  position: 'top-center',\n"
                    + "  icon: 'success',\n"
                    + "  title: '¡Aviso!',\n"
                    + "  text: '" + info + "',\n"
                    + "  showConfirmButton: false,\n"
                    + "  timer: 8000\n"
                    + "})");
            Thread.sleep(8000);

            // Configurar el archivo de salida
            merger.setDestinationFileName(tmpRuta.getRutaTemp());
            // Fusionar los PDFs
            merger.mergeDocuments(MemoryUsageSetting.setupMixed(400000000));
            System.out.println("PDFs fusionados correctamente.");

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
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
                    } else if (archivo.isFile() && archivo.getName().toLowerCase().trim().endsWith(extension.toLowerCase())) {
                        // Si es un archivo y tiene la extensión buscada, añadirlo a la lista
                        archivosEncontrados.add(archivo);
                    }
                }
            }
        }

        return archivosEncontrados;
    }

    public List<String> completeRFC(String rfc) throws SQLException {
        List<String> resultRFC = new ArrayList<>();
        List<String> listarTodo = new ArrayList<>();
        //ConectarPolizas();

        ConectarSae();
        
        PreparedStatement st = getCnSae().prepareStatement("SELECT DISTINCT (RFC) FROM CLIE01 WHERE  RFC LIKE '" + rfc + "%'");
        ResultSet rs = st.executeQuery();
        listarTodo = new ArrayList<>();
        if (!rs.isBeforeFirst()) {
            listarTodo.add("No hay resultados para tu búsqueda");
        } else {
            while (rs.next()) {
                listarTodo.add(this.resultadoRFC = rs.getString("RFC"));
            }
        }
        for (int i = 0; i < listarTodo.size(); i++) {
            resultRFC.add(listarTodo.get(i));
        }

        CerrarSae();
        return resultRFC;
    }

    
}
