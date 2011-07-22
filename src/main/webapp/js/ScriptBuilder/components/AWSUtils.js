/*
 * This file is part of the AuScope Virtual Exploration Geophysics Lab (VEGL) project.
 * Copyright (c) 2011 CSIRO Earth Science and Resource Engineering
 *
 * Licensed under the terms of the GNU Lesser General Public License.
 */
Ext.namespace("ScriptBuilder");
Ext.ux.ComponentLoader.load( {url : ScriptBuilder.componentPath + "AWSUtils.json"});

AWSUtilsNode = Ext.extend(ScriptBuilder.BasePythonComponent, {

    constructor : function(container) {
        AWSUtilsNode.superclass.constructor.apply(this, [ container,
                "AWS Utils Object", "AWSUtils", "s" ]);

        var numShells = container.getShellCommands().length;
        this.values.uniqueName = "AWSUtils" + numShells;
    },

    /**
     * This is where we dynamically generate a python Getter/Setter class from the job object that
     * is sent to us
     */
    getScript : function() {
        var text = '';

        text += '# ----- Autogenerated AWS Utility Functions -----' + this._newLine;
        text += '# Uploads inFilePath to the specified bucket with the specified key' + this._newLine;
        text += 'def awsUpload(inFilePath, awsBucket, awsKey):' + this._newLine;
        text += this._tab + 'queryPath = (awsBucket + "/" + awsKey).replace("//", "/")' + this._newLine;
        text += this._tab + 'retcode = subprocess.call(["aws", "put", queryPath, inFilePath, "--set-acl=public-read"])' + this._newLine;
        text += this._tab + 'print "awsUpload: " + inFilePath + " to " + queryPath + " returned " + str(retcode)' + this._newLine;
        text += this._newLine;
        text += '# downloads the specified key from bucket and writes it to outfile' + this._newLine;
        text += 'def awsDownload(awsBucket, awsKey, outFilePath):' + this._newLine;
        text += this._tab + 'queryPath = (awsBucket + "/" + awsKey).replace("//", "/")' + this._newLine;
        text += this._tab + 'retcode = subprocess.call(["aws", "get", queryPath, outFilePath])' + this._newLine;
        text += this._tab + 'print "awsDownload: " + queryPath + " to " + outFilePath + " returned " + str(retcode)' + this._newLine;
        text += '# -----------------------------------------------' + this._newLine;
        text += this._newLine;

        return text;
    }
});