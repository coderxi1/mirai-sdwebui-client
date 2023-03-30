package com.coderxi.sdwebui.client.model

import com.alibaba.fastjson2.annotation.JSONField
import com.coderxi.sdwebui.client.util.Alias

data class Txt2ImgParams(
    @Alias("enable_hr")
    @JSONField(name = "enable_hr")
    var enableHr: Boolean? = null,
    @Alias("denoising_strength", "重绘幅度")
    @JSONField(name = "denoising_strength")
    var denoisingStrength: Double? = null,
    @Alias("firstphase_width")
    @JSONField(name = "firstphase_width")
    var firstphaseWidth: Int? = null,
    @Alias("firstphase_height")
    @JSONField(name = "firstphase_height")
    var firstphaseHeight: Int? = null,
    @Alias("hr_scale")
    @JSONField(name = "hr_scale")
    var hrScale: Double? = null,
    @Alias("hr_upscaler")
    @JSONField(name = "hr_upscaler")
    var hrUpscaler: String? = null,
    @Alias("hr_second_pass_steps")
    @JSONField(name = "hr_second_pass_steps")
    var hrSecondPassSteps: Int? = null,
    @Alias("hr_resize_x")
    @JSONField(name = "hr_resize_x")
    var hrResizeX: Int? = null,
    @Alias("hr_resize_y")
    @JSONField(name = "hr_resize_y")
    var hrResizeY: Int? = null,
    @Alias("prompt", "正向提示词", "正提示词", "正向", "正", "阳")
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
    var subseed: Long? = null,
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
    var steps: Int? = 20,
    @Alias("cfg_scale", "CFG Scale", "CFG幅度")
    @JSONField(name = "cfg_scale")
    var cfgScale: Int? = 7,
    @Alias("width", "宽", "宽度", "图片宽度")
    @JSONField(name = "width")
    var width: Int? = 512,
    @Alias("height", "高", "高度", "图片高度")
    @JSONField(name = "height")
    var height: Int? = 512,
    @Alias("restore_faces", "面部修复")
    @JSONField(name = "restore_faces")
    var restoreFaces: Boolean? = false,
    @Alias("tiling", "平铺图")
    @JSONField(name = "tiling")
    var tiling: Boolean? = false,
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
    var scriptArgs: List<Any>? = null,
    @Alias("sampler_index", "采样方法索引")
    @JSONField(name = "sampler_index")
    var samplerIndex: String? = null,
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