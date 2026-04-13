# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/jcraffael/assignment7-jcraffael.git;protocol=ssh;branch=main \
			file://scull-start-stop"

# Modify these as desired
PV = "1.0+git"
SRCREV = "bdf6906af077ea1ca0ff12909216101f086c074a"

inherit module
#S = "${UNPACKDIR}"
S = "${WORKDIR}/git"
EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE += "M=${S}/scull"

inherit update-rc.d
#FILES:${PN} += "/lib/modules/${KERNEL_VERSION}/extra/scull.ko"
FILES:${PN} += "${bindir}/scull_load \
				${bindir}/scull_unload \
				${sysconfdir}/init.d/"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "scull-start-stop"
INITSCRIPT_NAME = "scull-start-stop"

#do_compile () {
#oe_runmake
#}
#do_configure:append() {
#    cp ${WORKDIR}/scull-start-stop ${S}/
#}
#do_install:append() {
#
#install -d ${D}${sysconfdir}/init.d
#install -m 0755 ${S}/scull-start-stop ${D}${sysconfdir}/init.d/scull-start-stop
#install -d ${D}${bindir}
#install -m 0755 ${S}/scull/scull_load ${D}${bindir}/scull_load
#install -m 0755 ${S}/scull/scull_unload ${D}${bindir}/scull_unload
#}

do_install:append() {
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/build
    rm -rf ${D}/lib/modules/${KERNEL_VERSION}/source
#
    rm -f ${D}/lib/modules/${KERNEL_VERSION}/modules.builtin
    rm -f ${D}/lib/modules/${KERNEL_VERSION}/modules.builtin.modinfo
    rm -f ${D}/lib/modules/${KERNEL_VERSION}/modules.order
	
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/scull-start-stop ${D}${sysconfdir}/init.d/scull-start-stop
	install -d ${D}${bindir}
	install -m 0755 ${S}/scull/scull_load ${D}${bindir}/scull_load
	install -m 0755 ${S}/scull/scull_unload ${D}${bindir}/scull_unload
}
