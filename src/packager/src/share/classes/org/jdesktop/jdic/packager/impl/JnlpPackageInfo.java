/*
 * Copyright (C) 2004 Sun Microsystems, Inc. All rights reserved. Use is
 * subject to license terms.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */ 

package org.jdesktop.jdic.packager.impl;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import com.sun.javaws.util.GeneralUtil;
import com.sun.javaws.jnl.XMLUtils;
import com.sun.deploy.xml.XMLNode;
import com.sun.deploy.xml.XMLParser;
import com.sun.javaws.exceptions.MissingFieldException;
import com.sun.javaws.exceptions.BadFieldException;

/**
 * This parameter class encapsulates all the description information for the
 * generated packages.
 */
public final class JnlpPackageInfo {

    /**
     * JnlpPackageInfo contructor.
     *
     */
    public JnlpPackageInfo() {
        titles = new String[JnlpConstants.LOCALES.length];
        vendors = new String[JnlpConstants.LOCALES.length];
        licenses = new String[JnlpConstants.LOCALES.length];
        descriptions = new String[JnlpConstants.LOCALES.length];
        uniqueTmpDirPath = null;
        enableLocalization = false;
        bannerJpgFilePath = null;
        panelJpgFilePath = null;
    }

    /**
     * Jnlp file title fields of different locales.
     */
    private String[] titles;
    /**
     * Jnlp file vendor fields of different locales.
     */
    private String[] vendors;
    /**
     * Jnlp file license fields of different locales.
     */
    private   String[] licenses;
    /**
     * Jnlp file description fields of different locales.
     */
    private   String[] descriptions;

    /**
     * License Dir path.
     */
    private String licenseDirPath;

    /**
     * Unique temporary dir path in system tmp dir.
     */
    private String uniqueTmpDirPath;

    /**
     * Package name. Such as: notepad.
     */
    private String packageName;

    /**
     *  The destination path where the package file would be placed.
     */
    private String outputDirPath;

    /**
     * Jnlp href, which is codebase + href.
     * Such as: http://java.sun.com/products/javawebstart/apps/notepad.jnlp
     */
    private String jnlpFileHref;

    /**
     * Path of the jnlp file. Such as: C:/temp/notepad.jnlp
     */
    private String jnlpFilePath;

    /**
     * Parent path of resource files referenced by the jnlp file,
     * Which should be given by users' input, it will be the parent
     * path of Jnlp file by default.
     * Such as: C:/temp
     */
    private String resourceDirPath;

    /**
     * Version Number of the application to be packaged.
     */
    private String version;

    /**
     * Release Number of the application to be packaged.
     */
    private String release;

    /**
     * Relative path list of resource files referenced by the jnlp file.
     * Such as: notepad/notepad.jar, notepad/images/notepad.jpg
     */
    private List jnlpRefFilePaths;

    /**
     * Absolutepath to Banner jpg file.
     */
    private String bannerJpgFilePath;

    /**
     * Absolutepath to Panel jpg file.
     */
    private String panelJpgFilePath;
    /**
     * Absolutepath to the MS SDK update.
     */
    private String msSDKDirPath;
    /**
     * Absolutepath to the raw msi file.
     */
    private String rawMsiFilePath;

    /**
     * If localization needed.
     */
    private boolean enableLocalization;

    /**
     * If show license or not.
     */
    private boolean enableLicense;

    /**
     * If the shortcut should be created
     */
    private boolean shortcutEnabled;

    /**
     * If the association should be created
     */
    private boolean enableAssociation;

    /**
     * If use system or user(default) cache.
     */
    private boolean enableSystemCache;

    /**
     * Retrieves the banner jpeg file path.
     * @return The banner jpeg file path.
     */
    public String getBannerJpgFilePath() {
        return bannerJpgFilePath;
    }

    /**
     * Sets the banner jpeg file path.
     * @param theBannerJpgFilePath The given banner jpeg file path.
     */
    public void setBannerJpgFilePath(String theBannerJpgFilePath) {
        bannerJpgFilePath = theBannerJpgFilePath;
    }

    /**
     * Gets the panel jpeg file path.
     * @return The panel jpeg file path.
     */
    public String getPanelJpgFilePath() {
        return panelJpgFilePath;
    }

    /**
     * Sets the panel jpeg file path.
     * @param thePanelJpegFilePath The given panel jpeg file path.
     */
    public void setPanelJpgFilePath(String thePanelJpegFilePath) {
        panelJpgFilePath = thePanelJpegFilePath;
    }
    /**
     * Gets the MS SDK Path.
     * @return The MS SDK Path.
     */
    public String getMSSDKDirPath() {
        return msSDKDirPath;
    }
    /**
     * Sets the MS SDK Path.
     * @param theMSSDKDirPath The given ms SDK path.
     */
    public void setMSSDKDirPath(String theMSSDKDirPath) {
        msSDKDirPath = theMSSDKDirPath;
    }
    /**
     * Gets the raw msi file path.
     * @return The raw msi file path.
     */
    public String getRawMsiFilePath() {
        return rawMsiFilePath;
    }

    /**
     * Sets the raw msi file path.
     * @param theRawMsiFilePath The given raw msi file path.
     */
    public void setRawMsiFilePath(String theRawMsiFilePath) {
        rawMsiFilePath = theRawMsiFilePath;
    }

    /**
     * Gets whether to install the application into system cache or not.
     * @return True if the application goes into the system cache.
     */
    public boolean getSystemCacheEnabled() {
        return enableSystemCache;
    }

    /**
     * Sets whether to install into the system cache.
     * @param systemcache True if the application will goes into the sys cache.
     */
    public void setSystemCacheEnabled(boolean systemcache) {
        enableSystemCache = systemcache;
    }

    /**
     * Gets the package name.
     * @return The name of the package.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the package name.
     * @param theName The given name of the package.
     */
    public void setPackageName(String theName) {
        packageName = theName;
    }

    /**
     * Gets the output directory path.
     * @return The path of the output directory.
     */
    public String getOutputDirPath() {
        return outputDirPath;
    }

    /**
     * Sets the output directory.
     * @param theOutputDirPath The path of the output directory.
     */
    public void setOutputDirPath(String theOutputDirPath) {
        outputDirPath = theOutputDirPath;
    }

    /**
     * Gets the jnlp file href.
     * @return The href of the jnlp file.
     */
    public String getJnlpFileHref() {
        return jnlpFileHref;
    }

    /**
     * Sets the jnlp file href.
     * @param theJnlpFileHref The given jnlp file href.
     */
    public void setJnlpFileHref(String theJnlpFileHref) {
        jnlpFileHref = theJnlpFileHref;
    }

    /**
     * Gest the jnlp file path.
     * @return The path of the jnlp file.
     */
    public String getJnlpFilePath() {
        return jnlpFilePath;
    }

    /**
     * Sets the jnlp file path.
     * @param theJnlpFilePath The given jnlp file path.
     */
    public void setJnlpFilePath(String theJnlpFilePath) {
        jnlpFilePath = theJnlpFilePath;
    }

    /**
     * Returns jnlp file name with extension of the jnlp file according to the
     * jnlpFilePath field.
     * <p>
     * For examples: if jnlpFilePath is C:/temp/notepad.jnlp, this field is
     * notepad.jnlp.
     * <p>
     * Since this field is generated from the jnlpFilePath field,
     * there is no associated variable member, and there is only getter method
     * for this field, no setter method.
     * @return The single jnlp file name without any parent dir info.
     */
    public String getJnlpFileName() {
        return new File(jnlpFilePath).getName();
    }

    /**
     * Gets the resource directory information.
     * @return The resource directory path.
     */
    public String getResourceDirPath() {
        return resourceDirPath;
    }

    /**
     * Sets the resource directory information.
     * @param theResourcePath The given resource directory path.
     */
    public void setResourcePath(String theResourcePath) {
        resourceDirPath = theResourcePath;
    }

    /**
     * Gets version info.
     * @return The version number string.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the version number info.
     * @param theVersion The given version number.
     */
    public void setVersion(String theVersion) {
        version = theVersion;
    }

    /**
     * Gets the release number info.
     * @return The release number info.
     */
    public String getRelease() {
        return release;
    }

    /**
     * Sets the release number.
     * @param theRelease The given release number.
     */
    public void setRelease(String theRelease) {
        release = theRelease;
    }

    /**
     * Gets the jnlp file refereced paths info.
     * @return The list iterator of jnlp file refereced paths.
     */
    public Iterator getJnlpRefFilePaths() {
        return jnlpRefFilePaths.iterator();
    }

    /**
     * Sets the jnlp file referenced paths.
     * @param theFilePaths The list containing all the referenced file paths.
     */
    public void setJnlpRefFilePaths(List theFilePaths) {
        // Question!!! need defensive copy here.
        jnlpRefFilePaths = theFilePaths;
    }

    /**
     * Gets whether to create a shortcut after the installation.
     * @return True if the installation will create a shortcut.
     */
    public boolean getShortcutEnabled() {
        return shortcutEnabled;
    }

    /**
     * Sets whether to create a shortcut during the installation process.
     * @param shortcut True if the installation will create a shortcut.
     */
    public void setShortcutEnabled(boolean shortcut) {
        shortcutEnabled = shortcut;
    }

    /**
     * Gets whether to create an association during the installation process.
     * @return True if the installation will create an association.
     */
    public boolean getAssociationEnabled() {
        return enableAssociation;
    }

    /**
     * Sets whether to create an association during the installation process.
     * @param association True if the installation will create an association.
     */
    public void setAssociationEnabled(boolean association) {
        enableAssociation = association;
    }

    /**
     * Gets whether we need to localize the installation package.
     * @return True if localization supported.
     */
    public boolean getLocalizationEnabled() {
        return enableLocalization;
    }

    /**
     * Sets whether we need to provide localization support.
     * @param globalization True if localization supported.
     */
    public void setGlocalizationEnabled(boolean globalization) {
        enableLocalization = globalization;
    }

    /**
     * Gets the license directory.
     * @return The directory containing all the license files.
     */
    public String getLicenseDirPath() {
        return licenseDirPath;
    }

    /**
     * Sets the license directory.
     * @param theLicenseDirPath The directory containing all the license files.
     */
    public void setLicenseDirPath(String theLicenseDirPath) {
        licenseDirPath = theLicenseDirPath;
    }

    /**
     * Sets whether to show license info during installation process.
     * @param bShow True if license info gets displayed durinig installation.
     */
    public void setShowLicense(boolean bShow) {
        enableLicense = bShow;
    }

    /**
     * Gets whether to show license info during installation process.
     * @return True if license info gets displayed during installation process.
     */
    public boolean getShowLicense() {
        return enableLicense;
    }

    /**
     * Gets a unique temp directory.
     * @return The path of the uniqe temp directory.
     * @throws IOException If failed to get such a directory.
     */
    public String getUniqueTmpDirPath() throws IOException {
        if (uniqueTmpDirPath == null) {
            uniqueTmpDirPath = FileOperUtility.createUniqueTmpDir();
        }
        return uniqueTmpDirPath;
    }

    /**
     * Gets localized jnlp file field.
     * @param locale The given locale name.
     * @param info  The name of the jnlp file field.
     * @return The lolized jnlp field content string.
     */
    public String getLocalizedJnlpInfo(String locale, String info) {
        int localeIndex = getLocaleIndex(locale);
        String ret = null;
        if (info.compareToIgnoreCase(JnlpConstants.JNLP_FIELD_TITLE) == 0) {
            ret = titles[localeIndex];
        } else if (
            info.compareToIgnoreCase(JnlpConstants.JNLP_FIELD_VENDOR) == 0) {
            ret = vendors[localeIndex];
        } else if (
            info.compareToIgnoreCase(JnlpConstants.JNLP_FIELD_LICENSE) == 0) {
            ret = licenses[localeIndex];
        } else if (
            info.compareToIgnoreCase(JnlpConstants.JNLP_FIELD_DESCRIPTION)
                == 0) {
            ret = descriptions[localeIndex];
        }
        return ret;
    }

    /**
     * Gets the corresponding locale index.
     * @param locale The given locale string.
     * @return The index of the locale string.
     */
    protected int getLocaleIndex(String locale) {
        int index = -1;
        for (int i = 0; i < JnlpConstants.LOCALES.length; i++) {
            if (locale.compareToIgnoreCase(JnlpConstants.LOCALES[i]) == 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Parsing the jnlp info.
     * @throws MissingFieldException If certain fields can't be located.
     * @throws BadFieldException If undefined fields found.
     */
    public void parseJnlpInfo()
        throws MissingFieldException, BadFieldException {
        // Get root of XML file
        XMLNode root;
        try {
            File jnlpFile = new File(jnlpFilePath);
            FileInputStream fis = new FileInputStream(jnlpFile);
            int fLen = (int) jnlpFile.length();
            byte[] bits = new byte[fLen];

            fis.read(bits, 0, fLen);
            fis.close();

            String content = new String(bits);
            root = (new XMLParser(content)).parse();
        } catch (Exception e) {
           return;
        }

        // Give value to title[], vendor[]
        XMLUtils.visitElements(root, "<information>",
                new XMLUtils.ElementVisitor() {
            public void visitElement(XMLNode e)
                throws BadFieldException, MissingFieldException {
                String[] localeList =
                    GeneralUtil.getStringList(
                        XMLUtils.getAttribute(e, "", "locale"));

                // if locale="", then use NO. 1, which is "us"
                // if locale!="", and it is not in locales list,
                // we use NO. 0, which is "reserved"
                int index =
                    (localeList == null) ? 0 : getLocaleIndex(localeList[0]);
                // If index in 10 listed languages, set the title and vendor
                if (index != -1) {
                    titles[index] = new String(
                            XMLUtils.getElementContents(e, "<title>"));
                    vendors[index] = new String(
                            XMLUtils.getElementContents(e, "<vendor>"));
                    descriptions[index] =
                            XMLUtils.getElementContents(e, "<description>");
                }
            }
        });

        // Make sure every info is filled
        for (int i = 1; i < JnlpConstants.LOCALES.length; i++) {
            titles[i]  = (titles[i] == null) ? titles[0] : titles[i];
            vendors[i] = (vendors[i] == null) ? vendors[0] : vendors[i];
            descriptions[i] = (descriptions[i] == null)
                            ? descriptions[0] : descriptions[i];
        }
    }
}
