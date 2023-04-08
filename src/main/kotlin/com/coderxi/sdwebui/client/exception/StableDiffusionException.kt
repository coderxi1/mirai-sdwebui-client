package com.coderxi.sdwebui.client.exception

import java.lang.RuntimeException

class StableDiffusionException(error: String, detail: String) : RuntimeException("$error|$detail")