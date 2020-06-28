FROM beevelop/cordova

ENV IONIC_VERSION 5.2.3
WORKDIR /src/app
COPY package.json package-lock.json ./
COPY . .
RUN apt-get update && apt-get install -y git bzip2 openssh-client && \
    npm i -g --unsafe-perm ionic@${IONIC_VERSION} && \
    ionic --no-interactive config set -g daemon.updates false && \
    rm -rf /var/lib/apt/lists/* && apt-get clean
EXPOSE 8100

ENTRYPOINT ["ionic"]
CMD ["serve", "8100", "--address", "0.0.0.0"]
