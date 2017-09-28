# { Ocelot.Online }
Ocelot is an Opencomputers emulator in your browser.

You don't need the Minecraft installation and OpenComputers modpack.
You don't need to download or install any programs.
You don't even need Java.

All that you need is a relatively modern web browser and Internet access.

This repository contains Ocelot.Online backend.

## Build & Run

Use `Ocelot` class to run the project.

The server will be binded to [http://0.0.0.0:37107/](http://0.0.0.0:37107/) address by default.

## Deploy

```sh
$ cd ocelot-tail
$ sbt assembly
```

All files necessary to run Ocelot server will be zipped to `./target/ocelot-x.x.x.jar`
