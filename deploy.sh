#!/bin/bash

cd `dirname $0`
BASE_DIR=`pwd`
BUILD_DIR=$BASE_DIR/build
DIST_DIR=$BASE_DIR/dist
GROUP_ID=org.codelibs.xerces
VERSION=2.12.0-sp1

java -version 2>&1|head -n1|grep 1.8
if [ $? != 0 ] ; then
  echo "Java 8 is required."
  java -version
  exit 1
fi

rm -rf $BUILD_DIR $DIST_DIR
mkdir $DIST_DIR
bash build.sh jar javadocs

cp $BUILD_DIR/xercesImpl.jar $DIST_DIR/xercesImpl-$VERSION.jar

cd $BUILD_DIR/docs/javadocs
zip $DIST_DIR/xercesImpl-$VERSION-javadoc.jar `find . -type f`

cd $BASE_DIR/src
zip $DIST_DIR/xercesImpl-$VERSION-sources.jar `find . -type f`

cd $BASE_DIR
cat $BASE_DIR/xercesImpl.pom | \
  sed -e "s/__GROUP_ID__/$GROUP_ID/" \
      -e "s/__VERSION__/$VERSION/" \
      > $DIST_DIR/xercesImpl-$VERSION.pom

echo $VERSION | grep SNAPSHOT > /dev/null
if [ $? != 0 ] ; then
  MVN_GOAL="gpg:sign-and-deploy-file -Durl=https://oss.sonatype.org/service/local/staging/deploy/maven2/ -DrepositoryId=sonatype-nexus-staging"
else
  MVN_GOAL="deploy:deploy-file -Durl=https://oss.sonatype.org/content/repositories/snapshots/ -DrepositoryId=sonatype-nexus-snapshots"
fi

mvn $MVN_GOAL -DgroupId=$GROUP_ID -DartifactId=xercesImpl -Dversion=$VERSION -Dpackaging=jar \
  -Dfile=$DIST_DIR/xercesImpl-$VERSION.jar \
  -DpomFile=$DIST_DIR/xercesImpl-$VERSION.pom \
  -Dsources=$DIST_DIR/xercesImpl-$VERSION-sources.jar \
  -Djavadoc=$DIST_DIR/xercesImpl-$VERSION-javadoc.jar

