FROM java:8
ADD build/libs/book-seller-0.0.1-SNAPSHOT.jar book-seller-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","book-seller-0.0.1-SNAPSHOT.jar" ]