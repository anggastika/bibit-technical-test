FROM ruby:2.6.5-slim-stretch

RUN apt-get update \
&&  apt-get upgrade -y \
&& apt-get install -y git \
&& apt-get install -y --no-install-recommends apt-utils \
   build-essential \
   libmariadb-dev

RUN gem install bundler:1.17.3

CMD [ "bundle" ]
