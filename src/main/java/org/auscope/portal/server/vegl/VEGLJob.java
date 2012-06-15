package org.auscope.portal.server.vegl;

import org.auscope.portal.core.cloud.CloudJob;

/**
 * A specialisation of a generic cloud job for the VEGL Portal
 *
 * A VEGL job is assumed to write all output to a specific cloud location
 * @author Josh Vote
 *
 */
public class VEGLJob extends CloudJob {
    private static final long serialVersionUID = -57851899164623641L;

    private String registeredUrl;
    private Integer seriesId;

    private String vmSubsetFilePath;
    private String vmSubsetUrl;
    private Double paddingMinEasting;
    private Double paddingMaxEasting;
    private Double paddingMinNorthing;
    private Double paddingMaxNorthing;
    private Double selectionMinEasting;
    private Double selectionMaxEasting;
    private Double selectionMinNorthing;
    private Double selectionMaxNorthing;
    private String mgaZone;
    private Integer cellX;
    private Integer cellY;
    private Integer cellZ;
    private Integer inversionDepth;

    /**
     * Creates an unitialised VEGLJob
     */
    public VEGLJob() {
        super();
    }

    /**
     * Creates a fully initialised VEGLJob
     * @param id ID for this job
     * @param description Description of this job
     * @param submitDate The date of submission for this job
     * @param user The username of whoever is running this job
     * @param emailAddress The contact email for whoever is running this job
     * @param status The descriptive status of this job
     * @param ec2InstanceId The ID of the running AMI instance (not the actual AMI ID).
     * @param ec2Endpoint The endpoint for the elastic compute cloud
     * @param ec2AMI The Amazon Machine Instance ID of the VM type that will run this job
     * @param cloudOutputAccessKey the access key used to connect to amazon cloud for storing output
     * @param cloudOutputSecretKey the secret key used to connect to amazon cloud for storing output
     * @param cloudOutputBucket the cloud bucket name where output will be stored
     * @param cloudOutputBaseKey the base key path (folder name) for all cloud output
     * @param registeredUrl Where this job has been registered for future reference
     * @param fileStorageId The ID of this job that is used for storing input/output files
     * @param vmSubsetFilePath The File path (on the VM) where the job should look for its input subset file
     * @param vmSubsetUrl The URL of the actual input subset file
     */
    public VEGLJob(Integer id) {
        super(id);
    }

    /**
     * Gets where this job has been registered
     * @return
     */
    public String getRegisteredUrl() {
        return registeredUrl;
    }

    /**
     * Sets where this job has been registered
     * @param registeredUrl
     */
    public void setRegisteredUrl(String registeredUrl) {
        this.registeredUrl = registeredUrl;
    }


    /**
     * Gets the ID of the series this job belongs to
     * @return
     */
    public Integer getSeriesId() {
        return seriesId;
    }

    /**
     * Sets the ID of the series this job belongs to
     * @param seriesId
     */
    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }


    /**
     * Gets the minimum easting of the padded bounds
     * @return
     */
    public Double getPaddingMinEasting() {
        return paddingMinEasting;
    }

    /**
     * Sets the minimum easting of the padded bounds
     * @param paddingMinEasting
     */
    public void setPaddingMinEasting(Double paddingMinEasting) {
        this.paddingMinEasting = paddingMinEasting;
    }

    /**
     * Gets the maximum easting of the padded bounds
     * @return
     */
    public Double getPaddingMaxEasting() {
        return paddingMaxEasting;
    }

    /**
     * Sets the maximum easting of the padded bounds
     * @param paddingMaxEasting
     */
    public void setPaddingMaxEasting(Double paddingMaxEasting) {
        this.paddingMaxEasting = paddingMaxEasting;
    }

    /**
     * Gets the minimum northing of the padded bounds
     * @return
     */
    public Double getPaddingMinNorthing() {
        return paddingMinNorthing;
    }

    /**
     * Sets the minimum northing of the padded bounds
     * @param paddingMinNorthing
     */
    public void setPaddingMinNorthing(Double paddingMinNorthing) {
        this.paddingMinNorthing = paddingMinNorthing;
    }

    /**
     * Gets the maximum northing of the padded bounds
     * @return
     */
    public Double getPaddingMaxNorthing() {
        return paddingMaxNorthing;
    }

    /**
     * Sets the maximum northing of the padded bounds
     * @param paddingMaxNorthing
     */
    public void setPaddingMaxNorthing(Double paddingMaxNorthing) {
        this.paddingMaxNorthing = paddingMaxNorthing;
    }

    /**
     * Gets the minimum easting of the selected bounds
     * @return
     */
    public Double getSelectionMinEasting() {
        return selectionMinEasting;
    }

    /**
     * Sets the minimum easting of the selected bounds
     * @param selectionMinEasting
     */
    public void setSelectionMinEasting(Double selectionMinEasting) {
        this.selectionMinEasting = selectionMinEasting;
    }

    /**
     * Gets the maximum easting of the selected bounds
     * @return
     */
    public Double getSelectionMaxEasting() {
        return selectionMaxEasting;
    }

    /**
     * Sets the maximum easting of the selected bounds
     * @param selectionMaxEasting
     */
    public void setSelectionMaxEasting(Double selectionMaxEasting) {
        this.selectionMaxEasting = selectionMaxEasting;
    }

    /**
     * Gets the minimum northing of the selected bounds
     * @return
     */
    public Double getSelectionMinNorthing() {
        return selectionMinNorthing;
    }

    /**
     * Sets the minimum northing of the selected bounds
     * @param selectionMinNorthing
     */
    public void setSelectionMinNorthing(Double selectionMinNorthing) {
        this.selectionMinNorthing = selectionMinNorthing;
    }

    /**
     * Gets the maximum northing of the selected bounds
     * @return
     */
    public Double getSelectionMaxNorthing() {
        return selectionMaxNorthing;
    }

    /**
     * Sets the maximum northing of the selected bounds
     * @param selectionMaxNorthing
     */
    public void setSelectionMaxNorthing(Double selectionMaxNorthing) {
        this.selectionMaxNorthing = selectionMaxNorthing;
    }

    /**
     * Gets the selected MGA zone of the padded bounds
     * @return
     */
    public String getMgaZone() {
        return mgaZone;
    }

    /**
     * Sets the selected MGA zone of the padded bounds
     * @param mgaZone
     */
    public void setMgaZone(String mgaZone) {
        this.mgaZone = mgaZone;
    }

    /**
     * Gets the selected cell size in the X direction
     * @return
     */
    public Integer getCellX() {
        return cellX;
    }

    /**
     * Sets the selected cell size in the X direction
     * @param cellX
     */
    public void setCellX(Integer cellX) {
        this.cellX = cellX;
    }

    /**
     * Gets the selected cell size in the Y direction
     * @return
     */
    public Integer getCellY() {
        return cellY;
    }

    /**
     * Sets the selected cell size in the Y direction
     * @param cellY
     */
    public void setCellY(Integer cellY) {
        this.cellY = cellY;
    }

    /**
     * Gets the selected cell size in the Z direction
     * @return
     */
    public Integer getCellZ() {
        return cellZ;
    }

    /**
     * Sets the selected cell size in the Z direction
     * @param cellZ
     */
    public void setCellZ(Integer cellZ) {
        this.cellZ = cellZ;
    }

    /**
     * Gets the selected inversion depth
     * @return
     */
    public Integer getInversionDepth() {
        return inversionDepth;
    }

    /**
     * Sets the selected inversion depth
     * @param inversionDepth
     */
    public void setInversionDepth(Integer inversionDepth) {
        this.inversionDepth = inversionDepth;
    }


    /**
     * Gets the File path (on the VM) where the job should look for its input subset file
     * @return
     */
    public String getVmSubsetFilePath() {
        return vmSubsetFilePath;
    }

    /**
     * Sets the File path (on the VM) where the job should look for its input subset file
     * @param vmSubsetFilePath
     */
    public void setVmSubsetFilePath(String vmSubsetFilePath) {
        this.vmSubsetFilePath = vmSubsetFilePath;
    }

    /**
     * Gets the actual URL where the VM should request its input subset file
     * @return
     */
    public String getVmSubsetUrl() {
        return vmSubsetUrl;
    }

    /**
     * Sets the actual URL where the VM should request its input subset file
     * @param vmSubsetUrl
     */
    public void setVmSubsetUrl(String vmSubsetUrl) {
        this.vmSubsetUrl = vmSubsetUrl;
    }

    @Override
    public String toString() {
        return "VEGLJob [registeredUrl=" + registeredUrl + ", seriesId="
                + seriesId + ", id=" + id + ", name=" + name + ", description="
                + description + "]";
    }
}