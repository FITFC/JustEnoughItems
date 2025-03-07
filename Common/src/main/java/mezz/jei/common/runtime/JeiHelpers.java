package mezz.jei.common.runtime;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IModIdHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.common.gui.GuiHelper;
import mezz.jei.common.platform.Services;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class JeiHelpers implements IJeiHelpers {
	private final GuiHelper guiHelper;
	private final IStackHelper stackHelper;
	private final IModIdHelper modIdHelper;
	private final IFocusFactory focusFactory;
	private final IPlatformFluidHelper<?> platformFluidHelper;
	private @Nullable Collection<IRecipeCategory<?>> recipeCategories;

	public JeiHelpers(
		GuiHelper guiHelper,
		IStackHelper stackHelper,
		IModIdHelper modIdHelper,
		IFocusFactory focusFactory
	) {
		this.guiHelper = guiHelper;
		this.stackHelper = stackHelper;
		this.modIdHelper = modIdHelper;
		this.focusFactory = focusFactory;
		this.platformFluidHelper = Services.PLATFORM.getFluidHelper();
	}

	public void setRecipeCategories(Collection<IRecipeCategory<?>> recipeCategories) {
		this.recipeCategories = Collections.unmodifiableCollection(recipeCategories);
	}

	@Override
	public IGuiHelper getGuiHelper() {
		return guiHelper;
	}

	@Override
	public IStackHelper getStackHelper() {
		return stackHelper;
	}

	@Override
	public IModIdHelper getModIdHelper() {
		return modIdHelper;
	}

	@Override
	public IFocusFactory getFocusFactory() {
		return focusFactory;
	}

	@Override
	public IPlatformFluidHelper<?> getPlatformFluidHelper() {
		return platformFluidHelper;
	}

	@Override
	public Optional<RecipeType<?>> getRecipeType(ResourceLocation uid) {
		return Optional.ofNullable(this.recipeCategories)
			.flatMap(r -> r.stream()
				.map(IRecipeCategory::getRecipeType)
				.filter(t -> t.getUid().equals(uid))
				.findFirst()
			);
	}
}
