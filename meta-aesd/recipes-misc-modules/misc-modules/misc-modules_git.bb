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
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/jcraffael/assignment7-jcraffael.git;protocol=ssh;branch=main \
			file://faulty-start-stop"

# Modify these as desired
PV = "1.0+git"
SRCREV = "a66b62c3c98f200f047420d3b3434c7d36bf8f0f"

inherit module
#S = "${UNPACKDIR}"
S = "${WORKDIR}/git"
EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"

inherit update-rc.d
FILES:${PN} += "${bindir}/module_load \
				${bindir}/module_unload \
				${sysconfdir}/init.d/"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "faulty-start-stop"
INITSCRIPT_NAME = "faulty-start-stop"

#do_compile () {
#oe_runmake
#}
#do_configure:append() {
#    cp ${WORKDIR}/faulty-start-stop ${S}/
#}

do_install:append () {
rm -rf ${D}/lib/modules/${KERNEL_VERSION}/build
rm -rf ${D}/lib/modules/${KERNEL_VERSION}/source
#
rm -f ${D}/lib/modules/${KERNEL_VERSION}/modules.builtin
rm -f ${D}/lib/modules/${KERNEL_VERSION}/modules.builtin.modinfo
rm -f ${D}/lib/modules/${KERNEL_VERSION}/modules.orde

install -d ${D}${sysconfdir}/init.d
install -m 0755 ${WORKDIR}/faulty-start-stop ${D}${sysconfdir}/init.d/faulty-start-stop
install -d ${D}${bindir}
install -m 0755 ${S}/misc-modules/module_load ${D}${bindir}/module_load
install -m 0755 ${S}/misc-modules/module_unload ${D}${bindir}/module_unload
}

