package com.krux.jenkins.runtimepublisher;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * HTML Report Representation
 * 
 * @author Andrei Varabyeu
 * 
 */
public class HtmlReport extends AbstractDescribableImpl<HtmlReport> {

	/** Report Directory **/
	private String directory;

	/** Index Page **/
	private String indexPage;

	/** Report Title **/
	private String reportTitle;

	@DataBoundConstructor
	public HtmlReport(String directory, String indexPage, String reportTitle) {
		super();
		this.directory = directory;
		this.indexPage = indexPage;
		this.reportTitle = reportTitle;
	}

	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 *            the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return the indexPage
	 */
	public String getIndexPage() {
		return indexPage;
	}

	/**
	 * @param indexPage
	 *            the indexPage to set
	 */
	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}

	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return reportTitle;
	}

	/**
	 * @param reportTitle
	 *            the reportTitle to set
	 */
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	@Extension
	public static class DescriptorImpl extends Descriptor<HtmlReport> {
		public String getDisplayName() {
			return "";
		}
	}

}
