package com.guochen.model;

import java.util.List;

public class TFileModel {
	
    private List<TFile> files;

	public List<TFile> getFiles() {
		return files;
	}

	public void setFiles(List<TFile> files) {
		this.files = files;
	}

	public TFileModel(List<TFile> files) {
        super();
        this.files = files;
    }
 
    public TFileModel() {
        super();
    }
	
}