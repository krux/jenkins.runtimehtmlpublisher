package com.krux.jenkins.runtimepublisher;

import hudson.FilePath;
import hudson.model.Action;
import hudson.model.DirectoryBrowserSupport;

import java.io.IOException;

import javax.servlet.ServletException;

import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Base Action. Performed during build execution - publishes some html
 * 
 * @author Andrei Varabyeu
 * 
 */
public class HtmlReportAction implements Action {

	/** Html Report representation */
	private HtmlReport htmlReport;

	/** Build root directory */
	private FilePath rootDirectory;

	public HtmlReportAction(FilePath rootDirectory, HtmlReport htmlDescription) {
		this.htmlReport = htmlDescription;
		this.rootDirectory = rootDirectory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hudson.model.Action#getIconFileName()
	 */
	public String getIconFileName() {
		return "graph.gif";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hudson.model.Action#getDisplayName()
	 */
	public String getDisplayName() {
		return htmlReport.getReportTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hudson.model.Action#getUrlName()
	 */
	public String getUrlName() {
		return htmlReport.getIndexPage();
	}

	/**
	 * @return Path to the directory where index file is placed
	 */
	public FilePath getIndexDirectoryPath() {
		return new FilePath(rootDirectory, htmlReport.getDirectory());
	}

	/**
	 * @return true if index file present in the workspace
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean isPresent() throws IOException, InterruptedException {
		return getIndexDirectoryPath().exists();
	}

	/**
	 * @return Index file checking timeout
	 */
	public Long getPageCheckTimeout() {
		return htmlReport.getPageCheckTimeout();
	}

	/**
	 * Serves HTML reports.
	 * 
	 * @param req
	 *            StaplerRequest
	 * @param rsp
	 *            StaplerResponse
	 * @throws IOException
	 * @throws ServletException
	 * 
	 * @see Stapler
	 * @see DirectoryBrowserSupport
	 */
	public void doDynamic(StaplerRequest req, StaplerResponse rsp)
			throws IOException, ServletException {
		DirectoryBrowserSupport dbs = new DirectoryBrowserSupport(this,
				getIndexDirectoryPath(), htmlReport.getReportTitle(),
				"graph.gif", false);
		dbs.setIndexFileName(htmlReport.getIndexPage());
		dbs.generateResponse(req, rsp, this);

	}

}
