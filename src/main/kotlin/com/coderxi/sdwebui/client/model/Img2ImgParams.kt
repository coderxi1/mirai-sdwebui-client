package com.coderxi.sdwebui.client.model

import com.alibaba.fastjson2.annotation.JSONField
import com.coderxi.sdwebui.client.util.Alias

data class Img2ImgParams(
    @Alias("init_images")
    @JSONField(name = "init_images")
    var initImages: List<String>? = null,
    @Alias("resize_mode")
    @JSONField(name = "resize_mode")
    var resizeMode: Int? = null,
    @Alias("denoising_strength", "重绘幅度")
    @JSONField(name = "denoising_strength")
    var denoisingStrength: Double? = null,
    @Alias("image_cfg_scale")
    @JSONField(name = "image_cfg_scale")
    var imageCfgScale: Int? = null,
    @Alias("mask")
    @JSONField(name = "mask")
    var mask: String? = null,
    @Alias("mask_blur")
    @JSONField(name = "mask_blur")
    var maskBlur: Int? = null,
    @Alias("inpainting_fill")
    @JSONField(name = "inpainting_fill")
    var inpaintingFill: Int? = null,
    @Alias("inpaint_full_res")
    @JSONField(name = "inpaint_full_res")
    var inpaintFullRes: Boolean? = null,
    @Alias("inpaint_full_res_padding")
    @JSONField(name = "inpaint_full_res_padding")
    var inpaintFullResPadding: Int? = null,
    @Alias("inpainting_mask_invert")
    @JSONField(name = "inpainting_mask_invert")
    var inpaintingMaskInvert: Int? = null,
    @Alias("initial_noise_multiplier")
    @JSONField(name = "initial_noise_multiplier")
    var initialNoiseMultiplier: Int? = null,
    @Alias("prompt", "prompts", "正向提示词", "正提示词", "正向", "正", "阳")
    @JSONField(name = "prompt")
    var prompt: String? = null,
    @Alias("styles")
    @JSONField(name = "styles")
    var styles: List<String>? = null,
    @Alias("seed", "种子", "随机种子", "随机数种子")
    @JSONField(name = "seed")
    var seed: Long? = -1,
    @Alias("subseed")
    @JSONField(name = "subseed")
    var subseed: Int? = null,
    @Alias("subseed_strength")
    @JSONField(name = "subseed_strength")
    var subseedStrength: Int? = null,
    @Alias("seed_resize_from_h")
    @JSONField(name = "seed_resize_from_h")
    var seedResizeFromH: Int? = null,
    @Alias("seed_resize_from_w")
    @JSONField(name = "seed_resize_from_w")
    var seedResizeFromW: Int? = null,
    @Alias("sampler_name", "sampler", "samplers", "采样方法", "采样器")
    @JSONField(name = "sampler_name")
    var samplerName: String? = null,
    @Alias("batch_size", "渲染个数", "批量")
    @JSONField(name = "batch_size")
    var batchSize: Int? = null,
    @Alias("n_iter")
    @JSONField(name = "n_iter")
    var nIter: Int? = null,
    @Alias("steps", "step", "步数", "渲染步数", "迭代步数", "步长", "渲染步长", "迭代步长")
    @JSONField(name = "steps")
    var steps: Int? = null,
    @Alias("cfg_scale", "CFG Scale", "CFG幅度")
    @JSONField(name = "cfg_scale")
    var cfgScale: Int? = null,
    @Alias("width", "宽", "宽度", "图片宽度")
    @JSONField(name = "width")
    var width: Int? = null,
    @Alias("height", "高", "高度", "图片高度")
    @JSONField(name = "height")
    var height: Int? = null,
    @Alias("restore_faces", "面部修复")
    @JSONField(name = "restore_faces")
    var restoreFaces: Boolean? = null,
    @Alias("tiling", "平铺图")
    @JSONField(name = "tiling")
    var tiling: Boolean? = null,
    @Alias("do_not_save_samples")
    @JSONField(name = "do_not_save_samples")
    var doNotSaveSamples: Boolean? = null,
    @Alias("do_not_save_grid")
    @JSONField(name = "do_not_save_grid")
    var doNotSaveGrid: Boolean? = null,
    @Alias("negative_prompt", "negative_prompts", "negative", "negative prompts", "反提示词", "反向提示词", "反向", "反", "阴")
    @JSONField(name = "negative_prompt")
    var negativePrompt: String? = "EasyNegative",
    @Alias("eta")
    @JSONField(name = "eta")
    var eta: Int? = null,
    @Alias("s_churn")
    @JSONField(name = "s_churn")
    var sChurn: Int? = null,
    @Alias("s_tmax")
    @JSONField(name = "s_tmax")
    var sTmax: Int? = null,
    @Alias("s_tmin")
    @JSONField(name = "s_tmin")
    var sTmin: Int? = null,
    @Alias("s_noise")
    @JSONField(name = "s_noise")
    var sNoise: Int? = null,
    @Alias("override_settings")
    @JSONField(name = "override_settings")
    var overrideSettings: Map<String, Any>? = null,
    @Alias("override_settings_restore_afterwards")
    @JSONField(name = "override_settings_restore_afterwards")
    var overrideSettingsRestoreAfterwards: Boolean? = null,
    @Alias("script_args")
    @JSONField(name = "script_args")
    var scriptArgs: List<Any?>? = null,
    @Alias("sampler_index", "采样方法索引")
    @JSONField(name = "sampler_index")
    var samplerIndex: String? = null,
    @Alias("include_init_images")
    @JSONField(name = "include_init_images")
    var includeInitImages: Boolean? = null,
    @Alias("script_name")
    @JSONField(name = "script_name")
    var scriptName: String? = null,
    @Alias("send_images")
    @JSONField(name = "send_images")
    var sendImages: Boolean? = null,
    @Alias("save_images", "保存图片")
    @JSONField(name = "save_images")
    var saveImages: Boolean? = false,
    @Alias("alwayson_scripts")
    @JSONField(name = "alwayson_scripts")
    var alwaysonScripts: Map<String, Any>? = null,
)