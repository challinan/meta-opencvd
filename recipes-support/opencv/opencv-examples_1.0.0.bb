DESCRIPTION = "OpenCV example programs based on The BDTI Quick-Start files"
HOMEPAGE = "http://www.embedded-vision.com/platinum-members/bdti/embedded-vision-training/documents/pages/OpenCVVMwareImage"
LICENSE = "Proprietary distributable with conditions"
DEPENDS = "opencv"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRC_URI = "file://opencv-examples-1.0.0.tar.gz"

# EXAMPLE_PROGRAMS = "cannyedgedetect facedetector linedetection motiondetection opticalflow"
EXAMPLE_PROGRAMS = " cannyedgedetect facedetector linedetection motiondetection opticalflow framework"

EXTRA_CXX_MAKE_FLAGS = " \
        -I${STAGING_DIR_TARGET}/opencv2/. \
        -I${STAGING_DIR_TARGET}/opencv2/release \
        -I${STAGING_DIR_TARGET}/opencv2/include \
        -I${STAGING_DIR_TARGET}/opencv2/include/opencv \
        -I${STAGING_DIR_TARGET}/opencv2/modules/core/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/flann/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/imgproc/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/video/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/highgui/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/ml/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/calib3d/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/features2d/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/objdetect/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/legacy/include \
        -I${STAGING_DIR_TARGET}/opencv2/modules/contrib/include "

LXX_FLAGS= " -Wl,-O1 -Wl,--hash-style=gnu \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_core.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_flann.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_imgproc.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_highgui.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_ml.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_video.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_objdetect.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_features2d.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_calib3d.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_legacy.so.2.4.9 \
        ${STAGING_DIR_TARGET}/usr/lib64/libopencv_contrib.so.2.4.9 \
        -ldl -lm -lpthread -lrt -lz -Wl,-rpath,${STAGING_DIR_TARGET}/usr/lib64"

do_compile() {
    bbnote "Entering do_compile"
    cd ${S}
    # ${CXX} ${CXXFLAGS} ${EXTRA_CXX_MAKE_FLAGS} -o cannyedgedetect -c cannyedgedetect.cpp
    # ${CXX} ${TARGET_CXXFLAGS} ${LXX_FLAGS} ${EXTRA_CXX_MAKE_FLAGS} -o cannyedgedetect cannyedgedetect.cpp
    echo "Compiling for ${EXAMPLE_PROGRAMS}"
    for i in ${EXAMPLE_PROGRAMS}; do
        echo "Compiling for $i"
        ${CXX} ${TARGET_CXXFLAGS} ${LXX_FLAGS} ${EXTRA_CXX_MAKE_FLAGS} -o $i $i.cpp 
    done
}

FILES_${PN} = " \
    ${bindir}/${PN}/cannyedgedetect \
    ${bindir}/${PN}/facedetector \
    ${bindir}/${PN}/framework \
    ${bindir}/${PN}/linedetection \
    ${bindir}/${PN}/motiondetection \
    ${bindir}/${PN}/opticalflow"

# inherit autotools

do_install() {
    install -d ${D}/${bindir}/${PN}
    for i in ${EXAMPLE_PROGRAMS}; do
        install -m 0755 ${S}/$i ${D}/${bindir}/${PN}
    done
#install -m 0755 ${S}/cannyedgedetect ${D}/${bindir}/${PN}
#    install -m 0755 ${S}/facedetector ${D}/${bindir}/${PN}
#   install -m 0755 ${S}/framework ${D}/${bindir}/${PN}
#    install -m 0755 ${S}/linedetection ${D}/${bindir}/${PN}
#    install -m 0755 ${S}/motiondetection ${D}/${bindir}/${PN}
#    install -m 0755 ${S}/opticalflow ${D}/${bindir}/${PN}
}

#EXTRA_OEMAKE = "-DNDEBUG -DPYTHON_USE_NUMPY=1 -I${STAGING_LIBDIR}/pymodules/python2.7/numpy/core/include"